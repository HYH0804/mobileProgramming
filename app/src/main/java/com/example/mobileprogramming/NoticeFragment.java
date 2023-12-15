package com.example.mobileprogramming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeFragment extends Fragment {

    private RecyclerView noticeListView;
    private ArrayList<Notice> noticeList;
    private NoticeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noticeListView = view.findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        adapter = new NoticeListAdapter(noticeList);
        noticeListView.setAdapter(adapter);

        final HashMap<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key", "2511a3196emsh38e0f00842675d1p117797jsn37d821d51900");

        List<String> urlList = new ArrayList<>();
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=33&last=1"); //맨유
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=39&last=1"); //울브스
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=40&last=1"); //리버풀
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=47&last=1"); //토트넘
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=42&last=1"); //아스날
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=49&last=1"); //첼시
        urlList.add("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&team=50&last=1"); // 맨시티

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        for (String url : urlList) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONArray responseArray = response.getJSONArray("response");
                                        JSONObject fixtureData = responseArray.getJSONObject(0);
                                        JSONObject fixture = fixtureData.getJSONObject("fixture");
                                        String data = fixture.getString("date");
                                        JSONObject teams = fixtureData.getJSONObject("teams");
                                        JSONObject homeTeam = teams.getJSONObject("home");
                                        JSONObject awayTeam = teams.getJSONObject("away");
                                        String homeTeamName = homeTeam.getString("name");
                                        String awayTeamName = awayTeam.getString("name");
                                        JSONObject goals = fixtureData.getJSONObject("goals");
                                        int homeGoals = goals.getInt("home");
                                        int awayGoals= goals.getInt("away");

                                        noticeList.add(new Notice(data, homeTeamName+" vs "+awayTeamName, String.valueOf(homeGoals)+":"+String.valueOf(awayGoals)));
                                        adapter.notifyDataSetChanged(); // This will refresh the RecyclerView

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return headers;
                }
            };

            queue.add(jsonObjectRequest);
        }
    }
}
