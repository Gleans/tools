package store.zabbix.common.zabbix;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yys
 *
 */
@Data
public class RequestBuilder {
    private static final AtomicInteger nextId = new AtomicInteger(1);

	private String jsonrpc = "2.0";

	private Map<String, Object> paramMap = new HashMap<>();

	private List<Integer> paramIdList=null;

	private String method;

	private String auth;

	private Integer id;

	private RequestBuilder(){

	}
	
	static public RequestBuilder newBuilder(){
		return new RequestBuilder();
	}
	
	public Map build(){
		Map resultMap=new HashMap();
		if(id == null){
			resultMap.put("id",nextId.getAndIncrement());
		}
		resultMap.put("jsonrpc",jsonrpc);
		resultMap.put("method",method);
		resultMap.put("auth",auth);
		if(paramIdList!=null&&paramIdList.size()>0){
			resultMap.put("params",paramIdList);
		}else{
			resultMap.put("params",paramMap);
		}

		return resultMap;
	}
	
	public RequestBuilder version(String version){
		this.jsonrpc=version;
		return this;
	}
	
	public RequestBuilder paramEntry(String key, Object value){
		this.paramMap.put(key, value);
		return this;
	}
	
	/**
	 * Do not necessary to call this method.If don not set id, ZabbixApi will auto set request auth.. 
	 * @param auth
	 * @return
	 */
	public RequestBuilder auth(String auth){
		this.auth=auth;
		return this;
	}
	
	public RequestBuilder method(String method){
		this.method=method;
		return this;
	}
	
	/**
	 * Do not necessary to call this method.If don not set id, RequestBuilder will auto generate.
	 * @param id
	 * @return
	 */
	public RequestBuilder id(Integer id){
		this.id=id;
		return this;
	}

	public RequestBuilder setParamIdList(List<Integer> idList){
		this.paramIdList=idList;
		return this;
	}

}
