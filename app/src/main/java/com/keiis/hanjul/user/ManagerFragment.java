package com.keiis.hanjul.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.keiis.hanjul.R;
import com.keiis.hanjul.vo.AgentVO;

import java.util.ArrayList;

/**
 * Created by kyh on 2017-01-29.
 */
public class ManagerFragment extends Fragment {
    public ManagerFragment() { }

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    //RecyclerView recyclerView;
    RecyclerView rv_judgement;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<AgentVO> agentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        final View fg_judgement = inflater.inflate(R.layout.fragment_judgement, container, false);

        rv_judgement = (RecyclerView)fg_judgement.findViewById(R.id.rv_judgement);
        linearLayoutManager = new LinearLayoutManager(rv_judgement.getContext());
        gridLayoutManager = new GridLayoutManager(rv_judgement.getContext(), 1);
        agentList.add(new AgentVO("1", "1010", "경민대"));
        agentList.add(new AgentVO("0", "1020", "서울시"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        agentList.add(new AgentVO("0", "1030", "도봉구"));
        setupRecyclerView(rv_judgement);

        return fg_judgement;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_judgement, container, false);
    }

    private void setupRecyclerView(RecyclerView recyclerView){
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), agentList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        ArrayList<AgentVO> agentList = new ArrayList<>();
        Context context;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final CheckBox cb_select;
            public final TextView tv_agentCd;
            public final TextView tv_agentName;

            public ViewHolder(View view) {
                super(view);
                cb_select    = (CheckBox) view.findViewById(R.id.cb_select);
                tv_agentCd   = (TextView) view.findViewById(R.id.tv_agentCd);
                tv_agentName = (TextView) view.findViewById(R.id.tv_agentName);
            }
        }

        public RecyclerViewAdapter(Context context, ArrayList<AgentVO> agentList) {
            this.context = context;
            this.agentList = agentList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            //holder.cb_select.setText(agentList.get(position).getSelect());
            if (agentList.get(position).getSelect().equals("1"))
                holder.cb_select.setChecked(true);
            else
                holder.cb_select.setChecked(false);
            holder.tv_agentCd.setText(agentList.get(position).getAgentCd());
            holder.tv_agentName.setText(agentList.get(position).getAgentName());
        }

        @Override
        public int getItemCount() {
            return agentList.size();
        }
    }
}
