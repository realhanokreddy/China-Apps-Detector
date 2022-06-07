package com.fy.chinaappsdetector;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InstalledApps extends AppCompatActivity{
    ListView listview;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean mIncludeSysApps;
    private final String[] chinaApps = {"AFK Arena", "APUS Browser", "APUS Flashlight-Free & Bright", "APUS Launcher -Theme, Call Show, Wallpaper, HideApps", "APUS Launcher Pro- Theme, Live Wallpapers, Smart", "APUS Message Center-Intelligent management", "APUS Security -Antivirus, Phone security, Cleaner", "APUS Turbo Cleaner 2020- Junk Cleaner, Anti-Virus", "Adore App", "AliExpress - Smarter Shopping, Better Living", "AliSuppliers Mobile App", "Alibaba Workbench", "Alipay", "Alipay Cashier", "AlipayHK", "Amour- video chat & call all over the world.", "AppLock", "AppLock Lite", "Arena of Valor: 5v5 Arena Games", "Art of Conquest: Dark Horizon", "AsianDate: find Asian singles", "Baidu", "Baidu Express Edition", "Baidu Translate", "Baidu map", "Beauty Camera Plus - Sweet Camera & Face Selfie", "Beauty Plus", "Bigo Live", "Bike Racing : Moto Traffic Rider Bike Racing Game", "BoxStar", "Buy Cars-offer everything you need, special offers and low prices", "CM Browser", "Cache Cleaner-DU App studio", "Cam Scanner", "CamCard - BCR (Western)", "CamCard - Business Card Reader", "CamCard - Business Card Reader", "CamCard Business", "CamCard for Salesforce", "CamOCR", "Carrom Friends : Carrom Board & Pool Game", "Cashier Wallet", "Chess Rush", "Chief Almighty: First Thunder BC", "ChinaLove: dating app for Chinese singles", "Chinese Social - Free Online Dating Video App & Chat", "Clash of Kings", "Clean Master– Cheetah Mobile", "Cleaner - Phone Booster", "Club Factory", "Conquista Online II", "Creative Destruction NetEase Games", "Crusaders of Light NetEase Games", "Cut Cut – Cut Out & Photo Background Editor", "Cyber Hunter", "Cyber Hunter Lite", "DU Browser", "DU Cleaner", "DU Privacy", "DU Recorder", "DU battery saver", "Dank Tanks", "Date in Asia - Dating & Chat For Asian Singles", "DateMyAge: Chat, Meet, Date Mature Singles Online", "Dawn of Isles", "DingTalk", "Drive with Lalamove India", "Dual Space - Multiple Accounts & App Cloner", "ES File Explorer", "FaceU - Inspire your Beauty", "Fighting Landlords - Free and happy Fighting Landlords", "First Love Live- super hot live beauties live online", "FlirtWish: chat with singles", "Free dating app- Singol, start your date!", "GO SMS Pro - Messenger, Free Themes, Emoji", "Gallery HD", "Gallery Vault - Hide Pictures And Videos", "Game of Sultans", "Government WeChat", "Guys Only Dating: Gay Chat", "HD Camera - Beauty Cam with Filters & Panorama", "HD Camera Pro & Selfie Camera", "HD Camera Selfie Beauty Camera", "HUYA LIVE – Game Live Stream", "Hago Play With New Friends", "Happy Fish", "Helo", "Heroes Evolved", "Hi Meitu", "Hide App-Hide Application Icon", "Identity V", "InNote", "Isoland 2: Ashes of Time", "Jellipop Match- Decorate your dream island!", "Kitty Live - Live Streaming & Video Live Chat", "Knives Out-No rules, just fight!", "Kwai", "Lalamove India - Delivery App", "Lamour Love All Over The World", "Learn Chinese AI-Super Chinese", "Legend: Rising Empire NetEase Games", "LifeAfter", "Likee", "Little Q Album", "LivU Meet new people & Video chat with strangers", "Lucky Live- Live Video Streaming App", "Ludo All Star- Play Online Ludo Game & Board Games", "Ludo World-Ludo Superstar", "MARVEL Super War NetEase Games", "MGTV- HunanTV official TV APP", "MICO Chat: New Friends Banaen aur Live Chat karen", "MV Master - Best Video Maker & Photo Video Editor", "MV Master - Make Your Status Video & Community", "Mafia City Yotta Games", "Mail Master", "Malay Social Dating App to Date & Meet Singles", "MangoTV", "Meitu", "Message Lock (SMS Lock)-Gallery Vault Developer Team", "Mi Community", "Mi Video Call–Xiaomi", "Mobile Legends", "Mobile Legends: Pocket", "Mobile Taobao", "Munchkin Match: magic home building", "Murderous Pursuits", "Music - Mp3 Player", "Music Player - Audio Player & 10 Bands Equalizer", "Music Player - Bass Booster - Free Download", "Music Player - MP3 Player & 10 Bands Equalizer", "Music player - Audio Player", "Netease News", "New Video Status", "Newsdog", "Onmyoji NetEase Games", "PUBG MOBILE LITE", "PUBG MOBILE Nordic Map: Livik", "Parallel Space", "Parallel Space Lite - Dual App", "Penguin E-sports Live assistant", "Penguin FM", "Photo Gallery & Album", "Photo Gallery HD & Editor", "Photo wonder", "Pitu", "QQ International", "QQ Launcher", "QQ Mail", "QQ Music", "QQ Newsfeed", "QQ Security Center", "ROMWE", "Rangers Of Oblivion : Online Action MMO RPG Game", "Rela - Lesbian Social Network", "Ride Out Heroes NetEase Games", "Rise of Kingdoms: Lost Crusade", "Road of Kings- Endless Glory", "Rules of Survival", "SelfieCity", "ShareSave by Xiaomi: Latest gadgets, amazing deals", "Shareit", "Shein", "Sina News", "Small Q brush", "Smart AppLock (App Protect)", "Snack Video", "Soul Hunters", "Soul- Follow the soul to find you", "Super Clean - Master of Cleaner, Phone Booster", "Super Mecha Champions", "Sweet Selfie", "Tantan - Date For Real", "Taobao Live", "Tencent Watchlist", "Tencent Weiyun", "TikTok", "TrulyAsian - Asian Dating App", "TrulyChinese - Chinese Dating App", "Tubit: Live Streams", "U Video", "U-Dictionary: Oxford Dictionary Free Now Translate", "UC Browser", "UC News", "Ulike - Define your selfie in trendy style", "V fly Status Video", "VPN for TikTok", "VPN for TikTok", "Vault- Hide", "Video Player - All Format HD Video Player", "Video Player All Format for Android", "Vigo Video", "Virus Cleaner", "Viva Video–QU Video Inc", "Vmate", "VooV Meeting - Tencent Video Conferencing", "Warpath", "We Date-Dating App", "We Meet", "We Work China", "WeChat", "WeChat Work", "WeChat reading", "WeSync", "WeTV - Cdrama, Kdrama&More", "WeTV - TV version", "WeTV Lite", "Web Browser & Fast Explorer", "Web Browser - Fast, Privacy & Light Web Explorer", "Web Browser - Secure Explorer", "Weibo", "Wonder Camera", "Xender", "Yimeng Jianghu-Chu Liuxiang has been fully upgraded", "YouCam makeup", "Youku", "Z Camera - Photo Editor, Beauty Selfie, Collage", "ZAKZAK LIVE: live-streaming & video chat app", "ZAKZAK Pro - Live chat & video chat online", "Zoom", "iPick"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_installed);
        listview = findViewById(R.id.listview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        listview.setTextFilterEnabled(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               refreshit();
            }
        });
    }
    private void refreshit(){
        LoadAppInfoTask loadAppInfoTask = new LoadAppInfoTask();
        loadAppInfoTask.execute(PackageManager.GET_META_DATA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadAppInfoTask loadAppInfoTask = new LoadAppInfoTask();
        loadAppInfoTask.execute(PackageManager.GET_META_DATA);
    }

    class LoadAppInfoTask extends AsyncTask<Integer, Integer, List<AppInfo>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);

        }

        @Override
        protected List<AppInfo> doInBackground(Integer... integers) {
            List<AppInfo> apps = new ArrayList<>();
            PackageManager pm = getPackageManager();
            List<ApplicationInfo> appInfoList = pm.getInstalledApplications(0);

            for(ApplicationInfo info: appInfoList){
                if(!mIncludeSysApps && (info.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    continue;
                }
                int l,r, mid;
                l = 0; r = chinaApps.length-1;
                while(l<=r) {



                    mid = (l + r) / 2;
                    int res = chinaApps[mid].compareTo(info.loadLabel(pm).toString());
                    if (res == 0) {
                        AppInfo app = new AppInfo();
                        app.info = info;
                        app.label = (String) info.loadLabel(pm);
                        apps.add(app);
                    } else if (res < 0) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }


            }
            return apps;
        }

        @Override
        public void onPostExecute(List<AppInfo> appInfos) {
            super.onPostExecute(appInfos);
            listview.setAdapter(new AppAdapter(getApplicationContext(), appInfos));
            swipeRefreshLayout.setRefreshing(false);
            Snackbar.make(listview, appInfos.size() + " china apps found", Snackbar.LENGTH_LONG).show();
            if(appInfos.size()==0){
                TextView tv = findViewById(R.id.congrats);
                tv.setVisibility(View.VISIBLE);
            }
        }
    }

}
