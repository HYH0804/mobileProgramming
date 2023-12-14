package com.example.mobileprogramming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private RecyclerView noticeListView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //fragment는 보여지기 위해 createView
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //뷰가 생성 완료 후 호출되는 함수, ACTIVITY 라이클사이크?랑 비슷한 느낌이나 다름
        super.onViewCreated(view, savedInstanceState);

        noticeListView = view.findViewById(R.id.noticeListView);
        ArrayList<Notice> noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("", "", " : "));
        NoticeListAdapter adapter = new NoticeListAdapter(noticeList);
        noticeListView.setAdapter(adapter);
    }
}
