package com.anuj;


import org.json.*;


import com.anuj.GroupServiceImpl;

public class GroupServiceImpl implements GroupService {
	
	private static GroupServiceImpl svc;
	
	public synchronized static GroupServiceImpl getInstance() {
		if (svc == null) {
			System.out.println("---> SVR: creating demo data");

			svc = new GroupServiceImpl();
		}
		return svc;
		}
	
	@Override
	public JSONArray getAllGroups() {
		MakingGroups m = new MakingGroups();
		JSONArray jsonArry = new JSONArray();
		jsonArry = m.makingGroups();
		return jsonArry;
	}

}