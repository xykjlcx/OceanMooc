package com.oceanli.oceanmooc.app.business.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.oceanli.ocean.core.delegates.OceanDelegate;
import com.oceanli.ocean.core.net.RestClient;
import com.oceanli.oceanmooc.app.OmConstant;
import com.oceanli.oceanmooc.app.R;
import com.oceanli.oceanmooc.app.business.user.models.NetUserModel;
import com.oceanli.oceanmooc.app.other.utils.IpGetUtil;
import com.oceanli.oceanmooc.app.other.utils.OmUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ocean on 2018/10/6
 * Author :  ocean
 * Email  :  348686686@qq.com
 */
public class IdeaBackDelegate extends OceanDelegate {
    @BindView(R.id.et_idea_back_content)
    EditText contentEd;
    @BindView(R.id.et_idea_back_contact)
    EditText contactEd;
    @BindView(R.id.tv_idea_back_ip)
    TextView ipAddressTv;
    @BindView(R.id.tv_idea_back_commit)
    TextView commitIdeaBackTv;

    private NetUserModel.DataBean userDataBean = null;

    @OnClick(R.id.tv_idea_back_commit)
    public void commitIdeaOnClick(){
        String contentStr = contentEd.getText().toString();
        String contactStr = contactEd.getText().toString();
        String addressStr = ipAddressTv.getText().toString();
        if (contentStr.equals("")
                || contactStr.equals("")
                || addressStr.equals("")){
            OmUtil.toastWarning(_mActivity,"请完整添加");
            return;
        }
        if (userDataBean != null){
            requestCommitIdeaBack(userDataBean.getId(),addressStr,contactStr,contentStr);
        }
    }

    public static IdeaBackDelegate newInstance(){
        IdeaBackDelegate ideaBackDelegate = new IdeaBackDelegate();
        return ideaBackDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_idea_back;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mImmersionBar.setStatusBarView(_mActivity, rootView.findViewById(R.id.view_my_idea_back_fill));
        userDataBean = OmUtil.getCacheUserInfo();
        initView();
    }

    public void initView(){
        ipAddressTv.setText(IpGetUtil.getIPAddress(_mActivity));
    }

    public void requestCommitIdeaBack(int userId,String address,String contact,String content){
        RestClient.builder()
                .url(OmConstant.BASE_URL + OmConstant.REQUEST_URL_POST_COMMIT_IDEA_BACK)
                .params("userId",userId)
                .params("address",address)
                .params("contact",contact)
                .params("content",content)
                .loader(_mActivity)
                .success(response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.get("code") == OmConstant.SUCCESS_CODE){
                            OmUtil.toastSuccess(_mActivity,jsonObject.getString("msg"));
                            pop();
                        }else {
                            OmUtil.toastError(_mActivity,jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                })
                .build()
                .post();
    }

}
