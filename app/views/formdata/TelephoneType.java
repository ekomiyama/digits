package views.formdata;

import java.util.HashMap;
import java.util.Map;


public class TelephoneType {

  private static String[] types = {"Work", "Home", "Mobile"};
  
  public static Map<String, Boolean> getTypes() {
    
    Map<String, Boolean> typeMap = new HashMap<>();
    
    for(String type : types) {
      typeMap.put(type,  false);
    }
    return typeMap;
  }
  
  
public static Map<String, Boolean> addType(String type) {
    
    Map<String, Boolean> typeMap = new HashMap<>();
    if(hasType(type)) {
    typeMap.put(type,  true);
    }
    return typeMap;
  }


public static boolean hasType(String type) {
  return TelephoneType.getTypes().keySet().contains(type);
}
  
  
}
