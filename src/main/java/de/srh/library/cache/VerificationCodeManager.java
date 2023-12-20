package de.srh.library.cache;


import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import de.srh.library.util.RandomCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerificationCodeManager {

  private static Logger logger = LoggerFactory.getLogger(VerificationCodeManager.class);
  // 30 seconds
  private static final Cache<String, String> verificationCodeCache =
      CacheUtil.newTimedCache(30 * 1000);

  public static String generateAndCacheVerificationCode(String email) {
    String verificationCode = RandomCodeGenerator.generateVerificationCode(4);
    verificationCodeCache.put(email, verificationCode);
    return verificationCode;
  }

  public static String getVerificationCode(String email) {
    return verificationCodeCache.get(email);
  }

}