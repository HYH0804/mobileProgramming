package com.example.mobileprogramming;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainPagerAdapter extends FragmentStateAdapter {


    SmsFragment smsFragment = new SmsFragment(); // 채팅방
    NoticeFragment noticeFragment = new NoticeFragment(); // 경기결과
    ProfileFragment profileFragment = new ProfileFragment(); // 사용자정보

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //ViewPager2의 Adapter에서 사용되는 메서드... 자동으로 호출됨.
        switch (position) {
            case 0:
                return noticeFragment;
            case 1:
                return smsFragment;
            default:
                return profileFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
