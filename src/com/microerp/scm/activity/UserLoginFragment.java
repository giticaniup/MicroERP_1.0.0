//package com.microerp.scm.activity;
//
//import android.app.Application;
//import android.content.Intent;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.widget.*;
//import com.ab.fragment.AbAlertDialogFragment;
//import com.ab.fragment.AbFragment;
//import com.ab.image.AbImageCache;
//import com.ab.image.AbImageLoader;
//import com.ab.model.AbMenuItem;
//import com.ab.task.AbTask;
//import com.ab.task.AbTaskItem;
//import com.ab.task.AbTaskListener;
//import com.ab.util.AbAnimationUtil;
//import com.ab.util.AbDialogUtil;
//import com.ab.util.AbFileUtil;
//import com.ab.util.AbToastUtil;
//import com.andbase.demo.activity.DemoMainActivity;
//import com.andbase.global.MyApplication;
//import com.andbase.im.activity.ContacterActivity;
//import com.andbase.im.activity.MessageActivity;
//import com.andbase.login.AboutActivity;
//import com.andbase.main.LeftMenuAdapter;
//import com.example.MicroERP_1_0_0.MainActivity;
//import com.example.MicroERP_1_0_0.R;
//import com.microerp.model.UserVO;
//
//import java.util.ArrayList;
//
///**
// * Created by zhongcy on 2015/4/28.
// */
//public class UserLoginFragment  extends AbFragment {
//    private MainActivity mActivity=null;
//    private MyApplication mapplication=null;
//    private TextView mNameText;
//    private  ExpandableListView mMenuListView;
//    private ImageView mUserPhoto;
//    private TextView mUserPoint;
//    private ImageView sunshineView;
//    private RelativeLayout loginLayout;
//    private AbImageLoader mAbImageLoader = null;
//    private ArrayList mGroupName;
//    private ArrayList mChild1;
//    private ArrayList mChild2;
//    private LeftMenuAdapter mAdapter;
//    private UserVO mUser=null;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //��ȡ��ǰActivity����
//        mActivity= (MainActivity) this.getActivity();
//        //��ȡapplication����
//        mapplication=mActivity.getApplication();
//        View view=inflater.inflate(R.layout.menu_left,container,false);
//        mMenuListView = (ExpandableListView) view.findViewById(R.id.menu_list);
//
//        mNameText = (TextView) view.findViewById(R.id.user_name);
//        mUserPhoto = (ImageView) view.findViewById(R.id.user_photo);
//        mUserPoint = (TextView) view.findViewById(R.id.user_point);
//        sunshineView = (ImageView) view.findViewById(R.id.sunshineView);
//        loginLayout = (RelativeLayout) view.findViewById(R.id.login_layout);
//        Button cacheClearBtn = (Button) view.findViewById(R.id.cacheClearBtn);
//        //����button�ļ���
//        cacheClearBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                AbDialogUtil.showProgressDialog(mActivity, 0, "������ջ���...");
//                AbTask task = new AbTask();
//                // �����첽ִ�еĶ���
//                final AbTaskItem item = new AbTaskItem();
//                item.setListener(new AbTaskListener() {
//
//                    @Override
//                    public void update() {
//                        AbDialogUtil.removeDialog(mActivity);
//                        AbToastUtil.showToast(mActivity, "������������");
//                    }
//
//                    @Override
//                    public void get() {
//                        try {
//                            AbFileUtil.clearDownloadFile();
//                            AbImageCache.getInstance().clearBitmap();
//                        } catch (Exception e) {
//                            AbToastUtil.showToastInThread(mActivity,
//                                    e.getMessage());
//                        }
//                    };
//                });
//                task.execute(item);
//
//            }
//        });
//
//        /**���ý��沼��
//         * �����Ϊ����ģ�飬��һ���Ǹ�����Ϣģ�飬mChild1������һ���û���Ϣadapter���ڶ����ǳ��ò���ģ�飬mChild2������һ��LeftMenuAdapter
//         *
//         */
//        mGroupName = new ArrayList<String>();
//        mChild1 = new ArrayList<AbMenuItem>();
//        mChild2 = new ArrayList<AbMenuItem>();
//
//        mAdapter = new LeftMenuAdapter(mActivity, mGroupName, mChild2);
//        mMenuListView.setAdapter(mAdapter);
//        for (int i = 0; i < mGroupName.size(); i++) {
//            mMenuListView.expandGroup(i);
//        }
//
//        mMenuListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                return true;
//            }
//        });
//        // ͼƬ������
//        mAbImageLoader = new AbImageLoader(mActivity);
//        mAbImageLoader.setMaxWidth(150);
//        mAbImageLoader.setMaxHeight(150);
//
//        initMenu();
//
//        AbAnimationUtil.playRotateAnimation(sunshineView, 2000, 5,
//                Animation.RESTART);
//
//        mAbImageLoader.setErrorImage(R.drawable.image_error);
//        mAbImageLoader.setEmptyImage(R.drawable.image_empty);
//
//        return  view;
//
//    }
//
//    /**
//     * �������û���������
//     *
//     * @param mNameText
//     */
//    public void setNameText(String mNameText) {
//        this.mNameText.setText(mNameText);
//    }
//
//    /**
//     * �����������û�����
//     *
//     * @param mPoint
//     */
//    public void setUserPoint(String mPoint) {
//        this.mUserPoint.setText(mPoint);
//        AbAnimationUtil.playRotateAnimation(sunshineView, 2000, 5,
//                Animation.RESTART);
//    }
//
//    public void downSetPhoto(String mPhotoUrl) {
//        // ����ͼƬ������
//        mAbImageLoader.setEmptyImage(R.drawable.photo01);
//        mAbImageLoader.setErrorImage(R.drawable.photo01_error);
//        mAbImageLoader.display(mUserPhoto, mPhotoUrl);
//    }
//
//    /**
//     * ����������ͷ��
//     *
//     */
//    public void setUserPhoto(int resId) {
//        this.mUserPhoto.setImageResource(resId);
//    }
//    public void initMenu() {
//        mGroupName.clear();
//        mChild1.clear();
//        mChild2.clear();
//
//        mGroupName.add("����");
//        mGroupName.add("����");
//
//        AbMenuItem m0 = new AbMenuItem();
//        m0.setIconId(R.drawable.square);
//        m0.setText("��ϵ��");
//        mChild1.add(m0);
//
//        AbMenuItem m1 = new AbMenuItem();
//        m1.setIconId(R.drawable.square);
//        m1.setText("�ҵ���Ϣ");
//        mChild1.add(m1);
//
//        AbMenuItem m3 = new AbMenuItem();
//        m3.setIconId(R.drawable.share);
//        m3.setText("������");
//        mChild1.add(m3);
//
//        AbMenuItem m4 = new AbMenuItem();
//        m4.setIconId(R.drawable.app);
//        m4.setText("Ӧ����Ϸ");
//        mChild1.add(m4);
//
//        AbMenuItem m5 = new AbMenuItem();
//        m5.setIconId(R.drawable.set);
//        m5.setText("֧����");
//        mChild2.add(m5);
//
//        AbMenuItem m6 = new AbMenuItem();
//        m6.setIconId(R.drawable.recommend);
//        m6.setText("�Ƽ�������");
//        mChild2.add(m6);
//
//
//        mUser = mapplication.mUser;
//        if (mUser != null) {
//            AbMenuItem m7 = new AbMenuItem();
//            m7.setIconId(R.drawable.quit);
//            m7.setText("ע��");
//            mChild2.add(m7);
//        }
//
//        AbMenuItem m8 = new AbMenuItem();
//        m8.setIconId(R.drawable.about);
//        m8.setText("����");
//        mChild2.add(m8);
//        mAdapter.notifyDataSetChanged();
//        for (int i = 0; i < mGroupName.size(); i++) {
//            mMenuListView.expandGroup(i);
//        }
//
//        if (mUser == null) {
//            setNameText("��¼");
//            setUserPhoto(R.drawable.photo01);
//            setUserPoint("0");
//            mNameText.setCompoundDrawables(null, null, null, null);
//            loginLayout.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    if (mUser == null) {
//                        mActivity.toLogin(mActivity.LOGIN_CODE);
//                    }
//                }
//            });
//        } else {
//            setNameText(mUser.getUserName());
//            downSetPhoto(mUser.getHeadUrl());
//            if ("MAN".equals(mUser.getSex())) {
//                Drawable d = mActivity.getResources().getDrawable(
//                        R.drawable.user_info_male);
//                d.setBounds(0, 0, 26, 26);
//                mNameText.setCompoundDrawables(null, null, d, null);
//            } else if ("WOMAN".equals(mUser.getSex())) {
//                Drawable d = mActivity.getResources().getDrawable(
//                        R.drawable.user_info_female);
//                d.setBounds(0, 0, 26, 26);
//                mNameText.setCompoundDrawables(null, null, d, null);
//            } else {
//                mNameText.setCompoundDrawables(null, null, null, null);
//            }
//
//            setUserPoint(String.valueOf(mUser.getPoint()));
//            loginLayout.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//
//                }
//            });
//        }
//        final String shareStr = this.getResources().getString(
//                R.string.share_desc);
//        setOnChangeViewListener(new OnChangeViewListener() {
//
//            @Override
//            public void onChangeView(int groupPosition, int childPosition) {
//                if (groupPosition == 0) {
//                    if (childPosition == 0) {
//                        // ��ϵ��
//                        if (application.mUser == null) {
//                            mActivity.toLogin(mActivity.FRIEND_CODE);
//                        } else {
//                            Intent intent = new Intent(mActivity,
//                                    ContacterActivity.class);
//                            mActivity.startActivity(intent);
//                        }
//                    } else if (childPosition == 1) {
//                        // �ҵ���Ϣ
//                        Intent intent = new Intent(mActivity,
//                                MessageActivity.class);
//                        startActivity(intent);
//                    } else if (childPosition == 2) {
//                        // ������
//                        Intent intent = new Intent(mActivity,
//                                DemoMainActivity.class);
//                        startActivity(intent);
//                    } else if (childPosition == 3) {
//                        // Ӧ����Ϸ
//                        mActivity.showApp();
//                    }
//                } else if (groupPosition == 1) {
//                    if (childPosition == 0) {
//                        // ѡ���������
//                        mActivity.showApp();
//                    } else if (childPosition == 1) {
//                        // �Ƽ�
//
//                    } else if (childPosition == 2) {
//                        if (mUser != null) {
//                            AbDialogUtil.showAlertDialog(mActivity, "ע��",
//                                    "ȷ��Ҫע�����û���?",
//                                    new AbAlertDialogFragment.AbDialogOnClickListener() {
//
//                                        @Override
//                                        public void onPositiveClick() {
//                                            // ע��
//                                            application.clearLoginParams();
//                                            initMenu();
//                                            mActivity.stopIMService();
//                                        }
//
//                                        @Override
//                                        public void onNegativeClick() {
//                                            // TODO Auto-generated method stub
//
//                                        }
//
//                                    });
//
//                        } else {
//                            // ����
//                            Intent intent = new Intent(mActivity,
//                                    AboutActivity.class);
//                            startActivity(intent);
//                        }
//                    } else if (childPosition == 3) {
//                        if (application.mUser != null) {
//                            // ����
//                            Intent intent = new Intent(mActivity,
//                                    AboutActivity.class);
//                            startActivity(intent);
//                        } else {
//                            // ��
//                        }
//                    }
//                }
//            }
//
//        });
//
//    }
//}
