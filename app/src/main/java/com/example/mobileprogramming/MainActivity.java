package com.example.mobileprogramming;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);  //하단 바 가져옴

        viewPager.setUserInputEnabled(false);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this); //()안에는 생성자, 프래그먼트를 관리할 수 있게 manager을 넣어줌
        viewPager.setAdapter(mainPagerAdapter); //프래그먼트 매니저 장착

        bottomNavigationView.setOnItemSelectedListener(item -> {    //하단 바 클릭 이벤트
            int id = item.getItemId();
                if (id==R.id.action_notice) {
                    bottomNavigationView.setItemIconTintList(new ColorStateList(
                            new int[][]{
                                    new int[]{android.R.attr.state_checked},
                                    new int[]{}
                            },
                            new int[]{
                                    ContextCompat.getColor(this, R.color.colorPink),
                                    ContextCompat.getColor(this, R.color.black)
                            }
                    )); //클릭시 색상 지정

                    viewPager.setCurrentItem(0, false); //보여줄 fragment


                } else if( id == R.id.action_chat) {
                    bottomNavigationView.setItemIconTintList(new ColorStateList(
                            new int[][]{
                                    new int[]{android.R.attr.state_checked},
                                    new int[]{}
                            },
                            new int[]{
                                    ContextCompat.getColor(this, R.color.colorPink),
                                    ContextCompat.getColor(this, R.color.black)
                            }
                    ));

                    viewPager.setCurrentItem(1, false);
                }

                else if(id== R.id.action_profile) {
                    bottomNavigationView.setItemIconTintList(new ColorStateList(
                            new int[][]{
                                    new int[]{android.R.attr.state_checked},
                                    new int[]{}
                            },
                            new int[]{
                                    ContextCompat.getColor(this, R.color.colorPink),
                                    ContextCompat.getColor(this, R.color.black)
                            }
                    ));

                    viewPager.setCurrentItem(2, false);
                }


            return true;
        });

        bottomNavigationView.setSelectedItemId(R.id.action_notice);
    }

    public void moveTab(@IdRes int id) {
        if (id != R.id.action_notice &&
                id != R.id.action_chat &&
                id != R.id.action_profile) {
            return;
        }

        bottomNavigationView.setSelectedItemId(id);
    }
}