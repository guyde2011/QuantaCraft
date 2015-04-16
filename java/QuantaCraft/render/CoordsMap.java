package QuantaCraft.render;

import java.util.HashMap;
import java.util.Map;

public class CoordsMap<T , R>{
	private Map<T,Map<T,Map<T,R>>> map;
	
	public CoordsMap(){
		map = new HashMap<T,Map<T,Map<T,R>>>();
	}
	
	public R get(T a , T b , T c){
		if (containsKeys(a,b,c)){
			return map.get(a).get(b).get(c);
		}
		return null;
	}
	
	public boolean containsKeys(T a , T b , T c){
		if (map.containsKey(a)){
			if (map.get(a).containsKey(b)){
				if (map.get(a).get(b).containsKey(c)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void put(T a , T b , T c , R d){
		if (!map.containsKey(a)){
			Map cMap = new HashMap<T,R>();
			Map bMap = new HashMap<T,Map<T,R>>();
			cMap.put(c, d);
			bMap.put(b, cMap);
			map.put(a, bMap);
			return;
		}
		if (!map.get(a).containsKey(b)){
			Map cMap = new HashMap<T,R>();
			Map bMap = map.get(a);
			cMap.put(c, d);
			bMap.put(b, cMap);
			map.put(a, bMap);
			return;
		}
		Map cMap = map.get(a).get(b);
		Map bMap = map.get(a);
		cMap.put(c, d);
		bMap.put(b, cMap);
		map.put(a, bMap);

	}
}
