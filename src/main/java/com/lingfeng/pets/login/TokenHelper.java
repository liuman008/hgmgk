package com.lingfeng.pets.login;

public interface TokenHelper {
    
    /**
     * 根据用户id生成token
     * @param id
     * @return
     */
    String create(String userName,String userPass,String userId);
    
    /**
     * 判断token是否有效
     * @author 谷春
     * @param 
     * @return boolean
     * @throws Exception
     */
    boolean verifyToke(String token);
    
    /**
     * 根据用户id删除token
     * @param mode
     * @return
     */
    boolean delete(String id);

}
