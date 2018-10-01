package com.oceanli.oceanmooc.app.callback;

import com.oceanli.oceanmooc.app.business.course.models.SectionChildModel;

/**
 * Created by ocean on 2018/9/29 Author :  ocean Email  :  348686686@qq.com
 */
public interface IOnCourseSectionChildOnclickCallBack {
    void onSectionChildClick(int adapterPos, int displayPos, SectionChildModel childModel);
}
