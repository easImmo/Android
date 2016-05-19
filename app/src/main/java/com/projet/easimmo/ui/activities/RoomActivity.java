package com.projet.easimmo.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.projet.easimmo.R;
import com.projet.easimmo.common.adapter.EquipmentAdapter;
import com.projet.easimmo.common.util.DividerItemDecoration;
import com.projet.easimmo.common.util.ItemClickSupport;
import com.projet.easimmo.dto.EquipmentDTO;
import com.projet.easimmo.dto.PropertyDTO;
import com.projet.easimmo.dto.RoomDTO;
import com.projet.easimmo.service.ICallback;
import com.projet.easimmo.service.manager.ServiceRooms;
import com.projet.easimmo.ui.dialogFragments.CreateEquipmentDialogFragment;
import com.projet.easimmo.ui.dialogFragments.CreateRoomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RoomActivity extends AppCompatActivity implements CreateEquipmentDialogFragment.CreateEquipmentCallback{

    @Bind(R.id.name_content)
    TextView mNameContent;

    @Bind(R.id.surface_content)
    TextView mSurfaceContent;

    @Bind(R.id.equipment_list)
    RecyclerView mRecyclerView;


    @Bind(R.id.retrofit_swype_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Bind(R.id.addEquipmentFab)
    FloatingActionButton mEquipmentFab;

    private List<EquipmentDTO> mEquipmentDTOList;
    private EquipmentAdapter mAdapter;
    private ServiceRooms serviceRooms;
    private RoomDTO room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        room = (RoomDTO) getIntent().getSerializableExtra("room");
        ButterKnife.bind(this);
        mNameContent.setText(room.getRoomTypeDTO());
        mSurfaceContent.setText("Surface : "+room.getmSurface()+" sq/m");
        setTitle("Visualisation de pièce");

        mRecyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mEquipmentDTOList = new ArrayList<>();
        mEquipmentDTOList.addAll(room.getEquipmentDTOList());

        mAdapter = new EquipmentAdapter(mEquipmentDTOList);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this));
        mRecyclerView.setAdapter(mAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }
        });

        serviceRooms = new ServiceRooms();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceRooms.getRoom(room.getmId(), new ICallback<RoomDTO>() {

                    @Override
                    public void success(RoomDTO roomDTO) {
                        mEquipmentDTOList.clear();
                        mEquipmentDTOList.addAll(roomDTO.getEquipmentDTOList());
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(Throwable error) {
                    }

                    @Override
                    public void unauthorized() {

                    }

                });
            }
        });

        if (mEquipmentFab != null) {
            mEquipmentFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = getSupportFragmentManager();
                    DialogFragment dialog = new CreateEquipmentDialogFragment();
                    Bundle bdl = new Bundle(1);
                    bdl.putString("room_id", room.getmId());
                    dialog.setArguments(bdl);
                    dialog.show(fm, "dialog");
                }
            });
        }

    }


    @Override
    public void onPOSTComplete() {
        serviceRooms.getRoom(room.getmId(), new ICallback<RoomDTO>() {

            @Override
            public void success(RoomDTO roomDTO) {
                mEquipmentDTOList.clear();
                mEquipmentDTOList.addAll(roomDTO.getEquipmentDTOList());
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void failure(Throwable error) {
            }

            @Override
            public void unauthorized() {

            }

        });
    }
}
