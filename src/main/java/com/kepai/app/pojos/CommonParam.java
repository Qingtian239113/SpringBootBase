package com.kepai.app.pojos;

import com.github.pagehelper.PageHelper;
import com.kepai.base.ResultException;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @ProjectName WineSpring
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/3/16
 * @note 这里写文件的详细功能和改动
 * @note
 */
public class CommonParam implements Serializable {

    /**
     * 用户登录凭证
     */
    public static final String TOKEN = "token";

    private String token;
    private String apiVer;
    private String terminal;

    /**
     * 分页处理
     */
    private Integer pageNo;
    private Integer offset;

    /**
     * 排序，格式 key.desc
     */
    private String sort;

    /**
     * 搜索字段，格式 search[key]=value
     */
    private HashMap<String, String> search;

    ///////////////////////////////////////////////////////////////////////////
    // 逻辑方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 构建一个查询实体
     *
     * @return
     */
    public Example.Criteria parseExample(Example example) {
        try {
            Example.Criteria criteria = example.createCriteria();
            if (!StringUtils.isEmpty(sort)) {
                String key = sort.substring(0, sort.indexOf("."));
                String value = sort.substring(key.length() + 1);
                if ("desc".equals(value)) {
                    example.orderBy(key).desc();
                } else {
                    example.orderBy(key).asc();
                }
            } else {
                example.orderBy("createTime").desc();
            }
            if (search != null && search.size() > 0) {
                for (Map.Entry<String, String> m : search.entrySet()) {
                    if (!StringUtils.isEmpty(m.getValue())) {
                        criteria.andLike(m.getKey(), "%" + m.getValue() + "%");
                    }
                }
            }
            return criteria;
        } catch (Exception e) {
            throw new ResultException(e.getMessage());
        }
    }

    /**
     * 转换为date
     *
     * @param time
     * @return
     */
    public Date parseDate(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        if (time.length() <= 11) {
            time = time + "000";
        }
        return new Date(Long.parseLong(time));
    }

    /**
     * 为空的数据统计转为null
     *
     * @param value
     * @return
     */
    public String parseNull(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value;
    }

    /**
     * 构建枚举对象enum
     *
     * @param eClass
     * @param value
     * @param <E>
     * @return
     */
    public <E extends Enum<E>> E parseEnum(Class<E> eClass, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Enum.valueOf(eClass, value);
        } catch (Exception e) {
            throw new ResultException("参数不在指定范围，请检查");
        }
    }

    /**
     * 启用物理分页
     *
     * @return
     */
    public void startPage() {
        if (StringUtils.isEmpty(pageNo)) {
            pageNo = 1;
        }
        if (StringUtils.isEmpty(offset)) {
            offset = 20;
        }
        PageHelper.startPage(pageNo, offset);
    }

    ///////////////////////////////////////////////////////////////////////////
    // setter,getter
    ///////////////////////////////////////////////////////////////////////////

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApiVer() {
        return apiVer;
    }

    public void setApiVer(String apiVer) {
        this.apiVer = apiVer;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public HashMap<String, String> getSearch() {
        return search;
    }

    public void setSearch(HashMap<String, String> search) {
        this.search = search;
    }
}
