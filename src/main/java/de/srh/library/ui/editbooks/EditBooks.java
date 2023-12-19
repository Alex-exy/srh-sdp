package de.srh.library.ui.editbooks;


import cn.hutool.core.exceptions.ValidateException;
import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.BookDto;
import de.srh.library.entity.Book;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.addnewbook.AddNewBook;
import de.srh.library.ui.editbookdata.EditBookData;
import de.srh.library.ui.managementmenu.ManagementMenu;
import de.srh.library.util.ValidatorUtils;
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
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Objects;

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

                if(bookIDField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a value in the ID field!");
                }
                else {
                    try{
                        ValidatorUtils.validateBookId(bookIDField.getText());
                    }catch (ValidateException ve ){
                        JOptionPane.showMessageDialog(null, ve.getMessage());
                        return;
                    }
                    long bookId = Long.parseLong(bookIDField.getText());
                    getBookById(bookId);
                    bookService = BookServiceImpl.createInstance();
                    BookDto bookDto = new BookDto();
                    bookDto.setBookId(bookId);
                    if (bookFound(bookDto)) {
                        EditBookData editBookData = new EditBookData(bookId);
                        editBookData.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Book does not exist! \nPlease try again!");
                    }
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
                        BookDto bookDto = assembleBook(row);
                        try{
                            bookService.insertBook(bookDto);
                            insertCount ++;
                        }catch (Exception e){
                            logger.error("book insert failed: " + bookDto, e);
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

    private static BookDto assembleBook(Row row) {
        BookDto bookDto = new BookDto();
        bookDto.setBookName(row.getCell(0).getStringCellValue());
        bookDto.setSubtitles(row.getCell(1).getStringCellValue());
        bookDto.setLanguage(row.getCell(2).getStringCellValue());
        bookDto.setIsbn(row.getCell(3).getStringCellValue());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        bookDto.setPublishDate(dateFormat.format(row.getCell(4).getDateCellValue()));
        bookDto.setBookAuthor(row.getCell(5).getStringCellValue());
        bookDto.setGenreId((int)(row.getCell(6).getNumericCellValue()));
        bookDto.setPrice(row.getCell(7).getStringCellValue());
        bookDto.setBookDescription(row.getCell(8).getStringCellValue());
        bookDto.setLibraryId((int)row.getCell(9).getNumericCellValue());
        bookDto.setDoi(row.getCell(10).getStringCellValue());
        return bookDto;
    }

    private ApiResponse getBookById(long bookId) {
        bookService = BookServiceImpl.createInstance();
        return bookService.getBookById((bookId));
    }

    public boolean bookFound(BookDto bookDto) {
        bookService = BookServiceImpl.createInstance();
        bookService.bookFound(bookDto);
        return bookService.bookFound(bookDto).getData() != 0;
    }


    public static void main(String[] args) {
        EditBooks editBooks = new EditBooks();
    }

}
