package de.srh.library.ui.editbooks;


import de.srh.library.dto.ApiResponse;
import de.srh.library.entity.Book;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.addnewbook.AddNewBook;
import de.srh.library.ui.editbookdata.EditBookData;
import de.srh.library.ui.login.LoginWindow;
import de.srh.library.ui.managementmenu.ManagementMenu;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

public class EditBooks extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(EditBooks.class);
    private JPanel editBooksWindow;
    private JButton goBack;
    private JLabel pageTitle;
    private JButton addNewBookButton;
    private JButton removeBookButton;
    private JButton editBookDataButton;
    private JTextField bookIDField;
    private JButton searchBookButton;
    private JButton importBooksButton;
    private BookService bookService;


    public EditBooks() {

        setAutoRequestFocus(false);
        setContentPane(editBooksWindow);
        setTitle("Edit Book Menu");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        logger.info("Opening edit books window ...");

        bookService = BookServiceImpl.createInstance();

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManagementMenu managementMenu = new ManagementMenu();
                managementMenu.setVisible(true);
            }
        });
        addNewBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewBook addNewBook = new AddNewBook();
                addNewBook.setVisible(true);
            }
        });

        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long bookId = Long.parseLong(bookIDField.getText());
                getBookById(bookId);


                    bookService = BookServiceImpl.createInstance();
                    if(bookFound()){
                        EditBookData editBookData = new EditBookData(bookId);
                        editBookData.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                    }
            }
        });

        importBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleImportButtonClicked();
            }
        });
    }

    private void handleImportButtonClicked() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream excelFile = new FileInputStream(fileChooser.getSelectedFile());
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet sheet = workbook.getSheetAt(0);

                int rowCount = sheet.getPhysicalNumberOfRows();
                int insertCount = 0;

                for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        Book book = assembleBook(row);
                        try{
                            bookService.insertBook(book);
                            insertCount ++;
                        }catch (Exception e){
                            logger.error("book insert failed: " + book, e);
                        }
                    }
                }
                workbook.close();
                JOptionPane.showMessageDialog(null, insertCount + " books imported successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error during data import: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static Book assembleBook(Row row) {
        Book book = new Book();
        book.setBookName(row.getCell(0).getStringCellValue());
        book.setSubtitles(row.getCell(1).getStringCellValue());
        book.setLanguage(row.getCell(2).getStringCellValue());
        book.setIsbn(row.getCell(3).getStringCellValue());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        book.setPublishDate(dateFormat.format(row.getCell(4).getDateCellValue()));
        book.setBookAuthor(row.getCell(5).getStringCellValue());
        book.setGenreId((int)(row.getCell(6).getNumericCellValue()));
        book.setPrice(row.getCell(7).getStringCellValue());
        book.setBookDescription(row.getCell(8).getStringCellValue());
        book.setLibraryId((int)row.getCell(9).getNumericCellValue());
        book.setDoi(row.getCell(10).getStringCellValue());
        return book;
    }

    private ApiResponse getBookById(long bookId) {
        bookService = BookServiceImpl.createInstance();
        return bookService.getBookById((bookId));
    }

    public boolean bookFound() {
        return bookService.bookFound(Long.parseLong(bookIDField.getText())).getData() == 1;
    }


    public static void main(String[] args) {
        EditBooks editBooks = new EditBooks();
    }

}
