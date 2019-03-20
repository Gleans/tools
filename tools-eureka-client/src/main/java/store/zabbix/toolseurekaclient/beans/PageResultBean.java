package store.zabbix.toolseurekaclient.beans;

import java.io.Serializable;

/*
 * description : 分页
 * @return
 * @time 2018-10-15 下午 9:29 晓风轻著
 **/
public class PageResultBean<T> extends ResultBean<T> implements Serializable {

    /**
     * 总记录数
     */
    private long totalRecord;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 当前页码
     */
    private int pageNo;

    /**
     * 当前页的记录数量
     */
    private int pageSize;

    public long getTotalRecord() {
        return totalRecord;
    }

    public PageResultBean setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        return this;
    }

    public int getPageCount() {
        return pageCount;
    }

    public PageResultBean setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public PageResultBean setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageResultBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @Override
    public String toString() {
        return "PageResultBean{" +
                "totalRecord=" + totalRecord +
                ", pageCount=" + pageCount +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

    @Override
    public PageResultBean setMsg(String msg) {
        super.setMsg(msg);
        return this;
    }

    @Override
    public PageResultBean setCode(int code) {
        super.setCode(code);
        return this;
    }

    @Override
    public PageResultBean setData(T data) {
        super.setData(data);
        return this;
    }
}
