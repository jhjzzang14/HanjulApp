package com.keiis.hanjul.organazation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.keiis.hanjul.NetworkModel.ContestGameList;
import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkModel.RecordDataResult;
import com.keiis.hanjul.NetworkModel.ResultList;
import com.keiis.hanjul.NetworkService.DefaultRestClient;
import com.keiis.hanjul.NetworkService.OrganizationService;
import com.keiis.hanjul.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hojun on 2017-05-01.
 */

public class RecordSubjectFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.sp_subject)
    Spinner spinnerView;

    DefaultRestClient<OrganizationService> defaultRestClient;

    OrganizationService organizationService;

    private String contestYY;
    private String contestSeq;
    //유저번호
    private String userNo;

    private String gameCd;
    private String organCd;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        contestYY = args.getString("contest_yy");
        contestSeq = args.getString("contest_seq");
        organCd = args.getString("organ_cd");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject_record,container,false);

        ButterKnife.bind(this,view);

        userNo = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("user_no","13");

        defaultRestClient = new DefaultRestClient<>();

        organizationService = defaultRestClient.getClient(OrganizationService.class);

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user_no",userNo);
            jsonObject.put("contest_yy",contestYY);
            jsonObject.put("contest_seq",contestSeq);
            jsonObject.put("people_cnt","30");
        }catch (Exception e){
            e.getStackTrace();
        }

        //대회 종목 목록 조회
        Call<DataResult<ContestGameList>> commit = organizationService.setContestGameList("ContestGameList",jsonObject);

        commit.enqueue(new Callback<DataResult<ContestGameList>>() {
            @Override
            public void onResponse(Call<DataResult<ContestGameList>> call, Response<DataResult<ContestGameList>> response) {
                if(response.isSuccessful()){
                    Log.d("결과",response.body().getDataArray().toString());

                    final List<ContestGameList> contests = response.body().getDataArray();

                    List<String> contestNames = new ArrayList<String>();
                    //대회 목록 저장
                    for(ContestGameList gameList : contests){
                        contestNames.add(gameList.getGame_name());
                    }

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, contestNames);

                    spinnerView.setAdapter(adapter);

                    //대회 목록 선택시
                    spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            gameCd = contests.get(position).getGame_cd();

                            JSONObject jsonObject = new JSONObject();
                            try{
                                jsonObject.put("user_no",userNo);
                                jsonObject.put("contest_yy",contestYY);
                                jsonObject.put("contest_seq",contestSeq);
                                jsonObject.put("result_gubn","ORGN");
                                jsonObject.put("orgn_cd",organCd);
                                jsonObject.put("game_cd",gameCd);
                                jsonObject.put("player_name","");
                            }catch (Exception e){
                                e.getStackTrace();
                            }

                            Call<RecordDataResult<ResultList>> commit = organizationService.setResultList("ResultList",jsonObject);
                            commit.enqueue(new Callback<RecordDataResult<ResultList>>() {
                                @Override
                                public void onResponse(Call<RecordDataResult<ResultList>> call, Response<RecordDataResult<ResultList>> response) {
                                    if(response.isSuccessful()){
                                        listView.setAdapter(new RecordSubjectAdapter(response.body().getDataArray()));
                                    }else{
                                        try{
                                            Log.d("result",response.errorBody().string());
                                        }catch (Exception e){
                                            e.getStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RecordDataResult<ResultList>> call, Throwable t) {
                                    List<ResultList> lists = new ArrayList<ResultList>();

                                    listView.setAdapter(new RecordSubjectAdapter(lists));
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DataResult<ContestGameList>> call, Throwable t) {
                Log.d("result",t.getMessage());
            }
        });
        return view;
    }

    class RecordSubjectAdapter extends BaseAdapter{

        private List<ResultList> resultLists;

        private LayoutInflater inflater;

        public RecordSubjectAdapter(List<ResultList> resultLists){
            this.resultLists = resultLists;
            inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return resultLists.size();
        }

        @Override
        public Object getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.record_subject_list_table_item,parent,false);

            //참가부 레이블
            TextView organizationView = (TextView)view.findViewById(R.id.tv_organization);
            //선수명 레이블
            TextView playerNameView = (TextView)view.findViewById(R.id.tv_player_name);
            //개수 레이블
            TextView countView = (TextView)view.findViewById(R.id.tv_cnt);
            //등급 레이블
            TextView rankView = (TextView)view.findViewById(R.id.tv_rank);

            organizationView.setText(resultLists.get(position).getPart_name());
            playerNameView.setText(resultLists.get(position).getPlayer_name());
            countView.setText(resultLists.get(position).getPart_rope_cnt()+"");
            rankView.setText(resultLists.get(position).getRank());
            //rankView.setText("rank");
            return view;
        }
    }
}
