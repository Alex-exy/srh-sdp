package de.srh.library.ui.borrowreturn;

import de.srh.library.constant.UserRole;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.BookDto;
import de.srh.library.dto.Global;
import de.srh.library.dto.UserDto;
import de.srh.library.entity.Borrow;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.service.borrow.BorrowService;
import de.srh.library.service.borrow.BorrowServiceImpl;
import de.srh.library.service.user.UserService;
import de.srh.library.service.user.UserServiceImpl;
import de.srh.library.ui.mainmenu.MainMenu;
import de.srh.library.util.DateUtil;
import de.srh.library.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BorrowReturn extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(BorrowReturn.class);
    private JPanel borrowReturnWindow;
    private JLabel pageTitle;
    private JList borrowedBookList;
    private JTextField borrowBookID;
    private JButton borrowButton;
    private JComboBox borrowDurationBox;
    private JTextField returnBookName;
    private JButton returnButton;
    private JButton extendButton;
    private JButton goBack;
    private JComboBox extendDurationBox;
    private JLabel selectedBook;

    private BorrowService borrowService;
    private UserService userService;

    private BookService bookService;
    private UserRole userRole;
    private UserDto userDto;

    private List<Borrow> borrowedBooks = new ArrayList<>();

    public BorrowReturn() {

        setAutoRequestFocus(false);
        setContentPane(borrowReturnWindow);
        setTitle("Borrow and Return");
        setSize(1280, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening borrow and return window ...");

        borrowService = BorrowServiceImpl.createInstance();
        userService = UserServiceImpl.createInstance();
        bookService = BookServiceImpl.createInstance();
        userDto = userService.getUserById(Global.loggedInUserId).getData();
        userRole = UserRole.getByRoleCode(userDto.getUserRole());

        refreshBorrowedBookList();
        setDurationListContent();

        borrowedBookList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Borrow borrowSelected =  (Borrow) borrowedBookList.getSelectedValue();
                String paneMessage = borrowSelected.toPaneMessage();
                if (e.getClickCount() == 2){
                    if (borrowSelected.getBorrowStatus() == 'D'){
                        BookDto bookDto = bookService.getBookById(borrowSelected.getBookId()).getData();
                        long overdueDays = DateUtil.daysToToday(borrowSelected.getExpectedReturnDate());
                        String fine = bookDto.getFine((int) overdueDays);
                        if ((Double.parseDouble(fine) > 0)){
                            paneMessage = paneMessage.substring(0, paneMessage.length() - 7);
                            paneMessage += "<br><br><font color='red'>Delayed Days: " + overdueDays + "</font>";
                            paneMessage += "<br><font color='red'>Fine: " + fine + "</font></html>";
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                            paneMessage, "Borrow Details", JOptionPane.INFORMATION_MESSAGE);
                }else if (e.getClickCount() == 1){
                    returnBookName.setText(borrowSelected.getBookName());
                    returnButton.setEnabled(true);
                    extendButton.setEnabled(true);
                    extendDurationBox.setEnabled(true);
                }
            }
        });
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (userDto.isInvalid()) {
                    JOptionPane.showMessageDialog(null, "Your account is invalid!");
                    return;
                }

                if (borrowedBooks.size() >= userRole.getMaxBorrowCount()) {
                    JOptionPane.showMessageDialog(null, "You have reached your maximum borrow limit!");
                    return;
                }
                String enteredBookID = borrowBookID.getText();
                try {
                    ValidatorUtils.validateBookId(enteredBookID);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                BookDto bookDto = bookService.getBookById(Long.valueOf(enteredBookID)).getData();
                if (bookDto == null) {
                    JOptionPane.showMessageDialog(null, "Book not found!");
                    return;
                }
                Borrow currentBorrow = borrowService.getByBookId(Long.valueOf(enteredBookID)).getData();
                if (currentBorrow != null && currentBorrow.getBorrowStatus() != 'R') {
                    JOptionPane.showMessageDialog(null, "Book already borrowed!");
                    return;
                }
                Borrow borrow = new Borrow();
                borrow.setBookId(bookDto.getBookId());
                borrow.setUserId(Global.loggedInUserId);
                borrow.setBorrowStatus('B');
                int monthsToBorrow = Integer.parseInt(borrowDurationBox.getSelectedItem().toString().split(" ")[0]);
                borrow.setExpectedReturnDate(Date.valueOf(LocalDate.now().plusMonths(monthsToBorrow)));
                ApiResponse apiResponse =  borrowService.insertBorrow(borrow);
                if (!apiResponse.isSuccess()) {
                    JOptionPane.showMessageDialog(null, apiResponse.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Book borrowed successfully!");

                refreshBorrowedBookList();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Borrow borrowSelected =  (Borrow) borrowedBookList.getSelectedValue();
                if (borrowSelected == null){
                    JOptionPane.showMessageDialog(null, "Please select a book to return!");
                    return;
                }

                returnABook(borrowSelected.getBorrowId());

                refreshBorrowedBookList();

                returnBookName.setText("");
                returnButton.setEnabled(false);
            }
        });
        extendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userDto.isInvalid()) {
                    JOptionPane.showMessageDialog(null, "Your account is invalid!");
                    return;
                }
                Borrow borrowSelected =  (Borrow) borrowedBookList.getSelectedValue();
                if (borrowSelected == null){
                    JOptionPane.showMessageDialog(null, "Please select a book to extend!");
                    return;
                }
                if (borrowSelected.getExtensions() >= userRole.getMaxExtensionCount()) {
                    JOptionPane.showMessageDialog(null, "You have reached your maximum extension limit!");
                    return;
                }
                int monthsToExtend = Integer.parseInt(extendDurationBox.getSelectedItem().toString().split(" ")[0]);
                Date expectedReturnDate =
                        Date.valueOf(new Date(borrowSelected.getExpectedReturnDate().getTime()).toLocalDate().plusMonths(monthsToExtend));
                ApiResponse apiResponse = borrowService.updateExtensionsAndExpectedReturnDate(borrowSelected.getBorrowId(),
                        borrowSelected.getExtensions() + 1, expectedReturnDate);
                if (!apiResponse.isSuccess()) {
                    JOptionPane.showMessageDialog(null, apiResponse.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Book extended successfully!");
                refreshBorrowedBookList();
                returnBookName.setText("");
                extendButton.setEnabled(false);
                extendDurationBox.setEnabled(false);
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        });
    }

    private void returnABook(long borrowId) {
        borrowService.updateBorrowStatus(borrowId, 'R');
        JOptionPane.showMessageDialog(null, "Book returned successfully!");
    }

    private List<Borrow> refreshBorrowedBookList() {
        List<Borrow> currentBorrowedBooks = new ArrayList<>();
        ApiResponse<List<Borrow>> response = borrowService.getOngoingBorrows(Global.loggedInUserId);
        if (response.isSuccess() && response.getData() != null){
            currentBorrowedBooks = response.getData();
        }
        borrowedBookList.setListData(currentBorrowedBooks.toArray(new Borrow[0]));
        borrowedBookList.setCellRenderer(new BorrowListCellRenderer());
        borrowedBooks = currentBorrowedBooks;
        return currentBorrowedBooks;
    }

    public void setDurationListContent() {
        int durationMonth = userRole.getMaxBorrowAndExtendMonths();
        for (int i = 1; i <= durationMonth; i++) {
            borrowDurationBox.addItem(i + " month");
            extendDurationBox.addItem(i + " month");
        }
    }

    public static void main(String[] args) {
        BorrowReturn borrowReturn = new BorrowReturn();
    }
}
