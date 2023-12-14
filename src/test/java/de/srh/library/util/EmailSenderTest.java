package de.srh.library.util;

import cn.hutool.core.collection.ListUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;



class EmailSenderTest {

  @Test
  @Timeout(value = 10 * 1000)
  void send() {
    EmailSender.send(ListUtil.toList("a.b@stud.hochschule-heidelberg.de"),
            "Reset Password - Heidelberg Library",
            "Click <a href = https://github.com/Alex-exy/srh-sdp/> here</a> to set a new password.",
            "password-reset-mail.html",
            true);
  }
}
