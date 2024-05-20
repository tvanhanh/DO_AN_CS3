package com.example.do_an_cs3.View.back_end.View_fragment.FragmentJob;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.do_an_cs3.Database.DatabaseManager;
import com.example.do_an_cs3.Model.Project;
import com.example.do_an_cs3.R;
import com.example.do_an_cs3.View.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewjobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewjobFragment extends Fragment {

    private RecyclerView recyclerViewProject;
    private ProjectAdapter projectAdapter;
    private List<Project> projectList;
    private DatabaseManager dbManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewjobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Newjob_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewjobFragment newInstance(String param1, String param2) {
        NewjobFragment fragment = new NewjobFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_updatenew, container, false);

        recyclerViewProject = view.findViewById(R.id.recyclerViewProject);
        projectList = new ArrayList<>();
        dbManager = new DatabaseManager(getActivity());

        // Lấy danh sách các dự án từ cơ sở dữ liệu SQLite
        projectList = dbManager.getAllProjects();

        // Khởi tạo và thiết lập ProjectAdapter với danh sách các dự án
        projectAdapter = new ProjectAdapter(projectList);

        // Thiết lập LinearLayoutManager cho RecyclerView
        recyclerViewProject.setLayoutManager(new LinearLayoutManager(getActivity()));


        // Gán Adapter vào RecyclerView
        recyclerViewProject.setAdapter(projectAdapter);

        return view;
    }
}