package com.sunnypoint.felicaexample.utils;

/**
 * Created by NhanCao on 07-Oct-15.
 */

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.sunnypoint.felicaexample.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class SoundManager {

    public static final int READER_TYPE = 1;
    public static final int BIRTHDAY_TYPE = 2;
    public static final int LOTTERY_TYPE = 3;
    private static final String TAG = "SoundManager";
    private static final int NORMAL_PRIORITY = 0;
    private static final String SOUNDS_PATH = "sounds/";
    private static SoundManager sInstance;
    private SparseArray<SoundInfos> mSoundInfos;
    private SoundPool mSoundPool;
    private Context mContext;
    private SoundModel mSelectedTouchSound;

    private SoundManager() {
        // Do nothing
    }

    public static SoundManager getInstance() {
        synchronized (SoundManager.class) {
            if (sInstance == null) {
                sInstance = new SoundManager();
            }
            return sInstance;
        }
    }

    public void init(Context context) {
        Log.i(TAG, "init..BEGIN");
        long begin = System.currentTimeMillis();
        mContext = context;
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        AssetManager am = context.getAssets();
        mSoundInfos = new SparseArray<SoundManager.SoundInfos>();

        String touchSoundName = null;
        String birthdaySoundName = null;
        String lotterySoundName = null;

        final int MAX_ATTEMPTS = 2;
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            Log.d(TAG, "init()..Retry " + i);
            try {
                // Load sound info from sound.json
                InputStream is = am.open(SOUNDS_PATH + "sound.json");
                String jsonStr = StreamUtils.readFully(is);
                SoundInfos[] soundInfos = new Gson().fromJson(jsonStr, SoundInfos[].class);
                for (SoundInfos si : soundInfos) {
                    mSoundInfos.put(si.type, si);
                    for (SoundModel sm : si.sounds) {
                        switch (si.type) {
                            case READER_TYPE: {
                                boolean equals = TextUtils.equals(sm.name, touchSoundName);
                                if (sm.is_default || equals) {
                                    load(sm, SOUNDS_PATH + si.path);
                                    if (equals || (touchSoundName == null && sm.is_default)) {
                                        mSelectedTouchSound = sm;
                                        // Save selected sound into preference
                                    }
                                }
                                break;
                            }

                            case BIRTHDAY_TYPE: {
                                boolean equals = TextUtils.equals(sm.name, birthdaySoundName);
                                if (sm.is_default || equals) {
                                    load(sm, SOUNDS_PATH + si.path);
                                    if (equals || (birthdaySoundName == null && sm.is_default)) {
                                        // Save selected sound into preference
                                    }
                                }
                                break;
                            }

                            case LOTTERY_TYPE: {
                                boolean equals = TextUtils.equals(sm.name, lotterySoundName);
                                if (sm.is_default || equals) {
                                    load(sm, SOUNDS_PATH + si.path);
                                    if (equals || (lotterySoundName == null && sm.is_default)) {
                                        // Save selected sound into preference
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                break;

            } catch (Exception e) {
                e.printStackTrace();
                // Error when open/load file
                if (i == MAX_ATTEMPTS) {
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (mSelectedTouchSound == null || mSelectedTouchSound.soundId == 0) {
            // Init touch sound default
            mSelectedTouchSound = new SoundModel();
            mSelectedTouchSound.soundId = mSoundPool.load(context, R.raw.sdefault, NORMAL_PRIORITY);
        }

        Log.v(TAG, "init()..END. Time " + (System.currentTimeMillis() - begin));
    }

    public ArrayList<SoundModel> getSoundInfos(int type, boolean forceLoad) {
        SoundInfos si = mSoundInfos.get(type);
        if (si != null) {
            ArrayList<SoundModel> soundInfos = new ArrayList<SoundManager.SoundModel>(si.sounds);
            Collections.sort(soundInfos);

            if (forceLoad) {
                for (SoundModel soundModel : soundInfos) {
                    load(soundModel, SOUNDS_PATH + si.path);
                }
            }

            return soundInfos;
        }
        return new ArrayList<SoundManager.SoundModel>();
    }

    public SoundModel getDefaultSound(int type) {
        SoundInfos si = mSoundInfos.get(type);
        if (si != null) {
            return si.getDefaultSound();
        }
        return null;
    }

    public void setSelectedTouchSound(SoundModel selectedSound) {
        mSelectedTouchSound = selectedSound;
    }

    private int load(int type, String name) {
        SoundInfos si = mSoundInfos.get(type);
        if (si != null) {
            SoundModel soundModel = si.getSound(name);
            if (soundModel != null) {
                load(soundModel, SOUNDS_PATH + si.path);
                return soundModel.soundId;
            }
        }
        return 0;
    }

    private void load(SoundModel soundModel, String path) {
        if (soundModel != null && soundModel.soundId == 0 && !TextUtils.isEmpty(soundModel.data)) {
            try {
                AssetFileDescriptor afd = mContext.getAssets().openFd(path + soundModel.data);
                soundModel.soundId = mSoundPool.load(afd, NORMAL_PRIORITY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void play() {
        // Play default touch sound
        if (mSelectedTouchSound.soundId != 0) {
            mSoundPool.play(mSelectedTouchSound.soundId, 1.0f, 1.0f, NORMAL_PRIORITY, 0, 1.0f);
        }
    }

    public void play(int type, String name) {
        if (name == null) {
            SoundModel defaultSound = getDefaultSound(type);
            if (defaultSound == null) {
                return;
            }
            name = defaultSound.name;
        }
        int soundId = load(type, name);
        if (soundId != 0) {
            Log.d(TAG, ".play() " + name);
            mSoundPool.play(soundId, 1.0f, 1.0f, NORMAL_PRIORITY, 0, 1.0f);
        }
    }

    private static class SoundInfos {

        public int type;
        public String path;
        public ArrayList<SoundModel> sounds;

        public SoundModel getSound(String name) {
            if (sounds != null) {
                for (SoundModel sm : sounds) {
                    if (sm.name.equalsIgnoreCase(name)) {
                        return sm;
                    }
                }
            }
            return null;
        }

        public SoundModel getDefaultSound() {
            if (sounds != null) {
                for (SoundModel sm : sounds) {
                    if (sm.is_default) {
                        return sm;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Sound info
     */
    public static class SoundModel implements Comparable<SoundModel> {

        public String data;
        public String name;
        public boolean is_default;
        public int soundId;

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(SoundModel another) {
            if (name.equalsIgnoreCase("ponpokopon")) {
                return 1;
            }
            if (another.name.equalsIgnoreCase("OFF")) {
                return 1;
            }
            return name.compareToIgnoreCase(another.name);
        }
    }
}
