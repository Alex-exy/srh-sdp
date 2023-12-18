package de.srh.library.util;

import cn.hutool.core.collection.ListUtil;
import de.srh.library.dto.OverdueBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class EmailSenderTest {

  @Test
  @Timeout(value = 10 * 1000)
  void send() {

    List<OverdueBook> books = new ArrayList<>();
    books.add(new OverdueBook("Book1", 7, "10"));
    books.add(new OverdueBook("Book2", 5, "8.5"));
    books.add(new OverdueBook("Book2", 100, "15.8"));

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("books", books);

    EmailSender.send(ListUtil.toList("kun.pang@stud.hochschule-heidelberg.de"),
            "Overdue Book Notification - Heidelberg Library",
            dataModel,
            "overdue-notification-mail.html",
            true);
  }
}
