package com.microerp.scm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.demo.adapter.UserDBListAdapter;
import com.andbase.demo.dao.UserSDDao;
import com.andbase.demo.model.LocalUser;
import com.andbase.global.MyApplication;
import com.example.MicroERP_1_0_0.R;
import com.microerp.adapter.PraybillAdapter;
import com.microerp.dao.Praybill;

import java.util.List;

/**
 * Created by zhongcy on 2015/4/24.
 */
public class caigou extends AbActivity {

    private MyApplication application;
    //�б�������
    private PraybillAdapter myListViewAdapter = null;
    //�б�����
    private List praybilllist = null;
    private ListView mListView = null;
    //�������ݿ����ʵ����
    private Praybill praybillDao = null;

    //ÿһҳ��ʾ������
    public int pageSize = 10;
    //��ǰҳ��
    public int pageNum = 1;
    //������
    public int totalCount = 0;
    //��ҳ��
    private LinearLayout mListViewForPage;
    //�������͵�ǰ��ʾ�ļ�ҳ
    public TextView total, current;
    //��һҳ����һҳ�İ�ť
    private Button preView,nextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.scm_qinggou);
        AbTitleBar mTittleBar=this.getTitleBar();
        mTittleBar.setTitleText("�빺��");
        mTittleBar.setLogo(R.drawable.button_selector_back);
        mTittleBar.setTitleBarBackground(R.drawable.top_bg);
        mTittleBar.setTitleTextMargin(10, 0, 0, 0);
        mTittleBar.setLogoLine(R.drawable.line);
        mTittleBar.clearRightView();
        mTittleBar.setLogo2(R.drawable.app_panel_add);
        mTittleBar.setLogo2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(caigou.this, qinggouAdd.class);
                startActivity(mIntent);
            }
        });
        praybillDao=new Praybill(caigou.this);

        //��ȡ���ݿ�����
        praybillDao.startReadableDatabase();
        //��ѯ���ݱ��浽praybilllist��
        praybilllist=praybillDao.queryList(null, null, null, null, null, "createtime desc limit "+
                String.valueOf(pageSize)+ " offset " +0, null);
        totalCount=praybillDao.queryCount(null, null);
        //�ر����ݿ�����
        praybillDao.closeDatabase();

        //��ȡListView����
        mListView = (ListView)this.findViewById(R.id.mListView);
        //��ҳ��
        mListViewForPage = (LinearLayout) this.findViewById(R.id.mListViewForPage);
        //��һҳ����һҳ�İ�ť
        preView = (Button) this.findViewById(R.id.preView);
        nextView = (Button) this.findViewById(R.id.nextView);
        //��ҳ����ʾ���ı�
        total = (TextView)findViewById(R.id.total);
        current = (TextView)findViewById(R.id.current);
        //����ƥ��һ��������������listviewƥ��������
        myListViewAdapter=new PraybillAdapter(this,praybilllist);
        mListView.setAdapter(myListViewAdapter);

        //�����Ƿ���ʾListViewForPage
        if(praybilllist==null || praybilllist.size()==0){
            mListViewForPage.setVisibility(View.GONE);
        }
        else{
            total.setText("������"+String.valueOf(totalCount));
            current.setText("��ǰҳ"+String.valueOf(pageNum));
            mListViewForPage.setVisibility(View.VISIBLE);
        }


        //��һҳ�¼�
        preView.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1){
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        preView();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //��һҳ�¼�
        nextView.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        nextView();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }


    /*
     * ��һҳ
     */
    private void preView() {
        pageNum--;
        current.setText("��ǰҳ:" + String.valueOf(pageNum));
        praybilllist.clear();

        queryData();
    }
    /*
     * ��һҳ
     */
    private void nextView() {
        pageNum++;
        current.setText("��ǰҳ:" + String.valueOf(pageNum));
        praybilllist.clear();

        queryData();
    }

    private void checkPageBar(){
        if(praybilllist == null || praybilllist.size()==0){
            //���������ط�ҳ��
            mListViewForPage.setVisibility(View.GONE);
        }else{
            queryDataCount();
        }
    }

    /**
     *
     * ��������ѯ����
     * @throws
     */
    public void queryData(){
        //(1)��ȡ���ݿ�
        praybillDao.startReadableDatabase();
        //(2)ִ�в�ѯ
        List ListNew = praybillDao.queryList(null, null, null, null, null, "create_time desc limit "+
                String.valueOf(pageSize)+ " offset " +String.valueOf((pageNum-1)*pageSize), null);
        //(3)�ر����ݿ�
        praybillDao.closeDatabase();

        praybilllist.clear();
        if(ListNew!=null){
            praybilllist.addAll(ListNew);
        }

        myListViewAdapter.notifyDataSetChanged();
        checkPageBar();
    }

    /**
     *
     * ��������ѯ����
     * @throws
     */
    public void queryDataCount(){
        //(1)��ȡ���ݿ�
        praybillDao.startReadableDatabase();
        //(2)ִ�в�ѯ
        totalCount = praybillDao.queryCount(null, null);
        //(3)�ر����ݿ�
        praybillDao.closeDatabase();

        total.setText("������:" + String.valueOf(totalCount));
        current.setText("��ǰҳ:" + String.valueOf(pageNum));
        mListViewForPage.setVisibility(View.VISIBLE);

    }

}
