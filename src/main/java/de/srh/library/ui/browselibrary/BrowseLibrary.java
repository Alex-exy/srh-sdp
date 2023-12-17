package de.srh.library.ui.browselibrary;


import de.srh.library.dto.ApiResponse;
import de.srh.library.dto.ApiResponseCode;
import de.srh.library.dto.BookDto;
import de.srh.library.service.book.BookService;
import de.srh.library.service.book.BookServiceImpl;
import de.srh.library.ui.createnewuser.CreateNewUser;
import de.srh.library.ui.mainmenu.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;


public class BrowseLibrary extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(CreateNewUser.class);
    private JPanel browseLibrary;
    private JLabel pageTitle;
    private JButton buttonSearch;
    private JTextField enterISBN;
    private JLabel labelISBN;
    private JTextField bookIDTextField;
    private JTextField bookTitleTextField;
    private JTextField bookAuthorTextField;
    private JComboBox searchLibrary;
    private JList<BookDto> resultsList;
    private JLabel labelResults;
    private JTextField doiTextField;
    private JTable resultsTableDisplay;
    private JButton goBack;
    private JComboBox searchGenre;
    private JScrollPane resultScrollPane;
    private JPanel resultPanel;
    private BookService bookService;
    private Map<String, Integer> genresMap;
    private Map<String,Integer> libraryMap;
   private Map<Long,List<String>> isbnBooks;
   private Map<Long,List<String>> nameBooks;
   private Map<Long,List<String>> authorBooks;
    private Map<Long,List<String>> bookGenre;
    private Map<Long,List<String>> bookLibrary;
    public BrowseLibrary() {

        bookService = BookServiceImpl.createInstance();
        getLibraries();
        getGenres();


        resultsTableDisplay.setModel(getjTableDefault());
        mouseClick(resultsTableDisplay);

        searchGenre.addItem("None");
        searchGenre.setSelectedItem("None");
        searchLibrary.addItem("None");
        searchLibrary.setSelectedItem("None");
        setAutoRequestFocus(false);
        setContentPane(browseLibrary);
        setTitle("Browse Library");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        logger.info("Opening create browse library window ...");



        buttonSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(bookTitleTextField.getText().trim().isEmpty() && bookAuthorTextField.getText().trim().isEmpty()
                        && enterISBN.getText().trim().isEmpty() && doiTextField.getText().trim().isEmpty()
                        && bookIDTextField.getText().trim().isEmpty() && searchGenre.getSelectedItem().equals("None")
                        && searchLibrary.getSelectedItem().equals("None")){
                    JOptionPane.showMessageDialog(null, "Please enter or select atleast 1 search parameter");
                    resultsTableDisplay.setModel(getjTableDefault());
                }
                else if(!bookIDTextField.getText().trim().isEmpty()){

                    long bookId = Long.parseLong(bookIDTextField.getText());
                    BookDto bookDto = new BookDto();
                    bookDto.setBookId(bookId);
                    if(bookFound(bookDto)){
                        resultsTableDisplay.setModel(getjTableId());
                    }else{
                        resultsTableDisplay.setModel(getjTableDefault());
                    }
                }
                else if(!enterISBN.getText().trim().isEmpty()){
                    BookDto bookDto = new BookDto();
                    bookDto.setIsbn(enterISBN.getText().trim());
                    if(bookFound(bookDto)){
                        resultsTableDisplay.setModel(getJTableIsbn());
                    }else {
                        resultsTableDisplay.setModel(getjTableDefault());
                    }

                }
                else if(!doiTextField.getText().trim().isEmpty()){
                    BookDto bookDto = new BookDto();
                    bookDto.setDoi(doiTextField.getText().trim());
                    if(bookFound(bookDto)){
                        resultsTableDisplay.setModel(getJTableDoi());
                    }else{
                        resultsTableDisplay.setModel(getjTableDefault());
                    }

                }else if(!bookTitleTextField.getText().trim().isEmpty()){
                    BookDto bookDto = new BookDto();
                    bookDto.setBookName(bookTitleTextField.getText().trim());
                    if(bookFound(bookDto)){
                        resultsTableDisplay.setModel(getJTableName());
                    }else{
                        resultsTableDisplay.setModel(getjTableDefault());
                    }
                }else if(!bookAuthorTextField.getText().trim().isEmpty()){
                    BookDto bookDto = new BookDto();
                    bookDto.setBookAuthor(bookAuthorTextField.getText().trim());
                    if(bookFound(bookDto)){
                        resultsTableDisplay.setModel(getJTableAuthor());
                    }else{
                        resultsTableDisplay.setModel(getjTableDefault());
                    }

                }else if(!searchGenre.getSelectedItem().equals("None")){
                    BookDto bookDto = new BookDto();
                    long bookId = bookDto.getBookId();
                    bookDto.getGenreName(bookId);
                    resultsTableDisplay.setModel(getJTableGenre());
                }else if(!searchLibrary.getSelectedItem().equals("None")){
                    BookDto bookDto = new BookDto();
                    long bookId = bookDto.getBookId();
                    bookDto.getLibraryName(bookId);
                    resultsTableDisplay.setModel(getJTableLibrary());
                }else {
                    resultsTableDisplay.setModel(getjTableDefault());
                }
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
    private void mouseClick(JTable jTable){
        jTable.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int row = jTable.getSelectedRow();
                    int column = jTable.getSelectedColumn();
                    if (row != -1 && column != -1) {
                        String cellValue = jTable.getValueAt(row, column).toString();
                        System.out.println("Double-clicked cell value: " + cellValue);
                    }
            }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private  TableModel getjTableId() {
        String[] columnNames = {"Book ID", "Title","Book Author","Genre","ISBN","DOI","In Library"};
        bookService = BookServiceImpl.createInstance();
        long bookId = Long.parseLong(bookIDTextField.getText().trim());
        BookDto bookDto = bookService.getBookById(bookId).getData();
        Object[][] data = {
                {bookDto.getBookId(),bookDto.getBookName(),bookDto.getBookAuthor(),bookDto.getGenreName(bookId),
                bookDto.getIsbn(),bookDto.getDoi(),bookDto.getLibraryName(bookId)}
        };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        return tableModel;
    }
    private  TableModel getJTableIsbn() {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long,List<String>>> apiResponseIsbn = bookService.getBookByIsbn(enterISBN.getText().trim());
        if(ApiResponseCode.SUCCESS.getCode() == apiResponseIsbn.getCode()){
            isbnBooks = apiResponseIsbn.getData();
            for(Map.Entry<Long,List<String>> entry : isbnBooks.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }
    private  TableModel getJTableName() {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long,List<String>>> apiResponseName = bookService.getBookByName(bookTitleTextField.getText().trim());

        if(ApiResponseCode.SUCCESS.getCode() == apiResponseName.getCode()){
            nameBooks = apiResponseName.getData();
            for(Map.Entry<Long,List<String>> entry : nameBooks.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }

    private  TableModel getJTableGenre() {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long,List<String>>> apiResponseGenre = bookService.bookByGenre(genresMap.get(searchGenre.getSelectedItem().toString()));

        if(ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()){
            bookGenre = apiResponseGenre.getData();
            for(Map.Entry<Long,List<String>> entry : bookGenre.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }

    private  TableModel getJTableLibrary() {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long,List<String>>> apiResponseLibrary = bookService.bookByLibrary(libraryMap.get(searchLibrary.getSelectedItem().toString()));

        if(ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()){
            bookLibrary = apiResponseLibrary.getData();
            for(Map.Entry<Long,List<String>> entry : bookLibrary.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }
    private  TableModel getJTableDoi() {
        String[] columnNames = {"Book ID", "Title","Book Author","Genre","ISBN","DOI","In Library"};
        bookService = BookServiceImpl.createInstance();
        BookDto bookDto = bookService.getBookByDoi(doiTextField.getText().trim()).getData();
            Object[][] data = {
                    {bookDto.getBookId(), bookDto.getBookName()}
            };
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            return tableModel;

    }

    private  TableModel getJTableAuthor() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Book Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("DOI");
        tableModel.addColumn("In Library");
        bookService = BookServiceImpl.createInstance();
        ApiResponse<Map<Long, List<String>>> apiResponseAuthor = bookService.getBookByAuthor(bookAuthorTextField.getText().trim());

        if(ApiResponseCode.SUCCESS.getCode() == apiResponseAuthor.getCode()){

            authorBooks = apiResponseAuthor.getData();
            for(Map.Entry<Long,List<String>> entry : authorBooks.entrySet()) {
                List<String> values = entry.getValue();
                Object[] rowData = new Object[7];
                rowData[0] = entry.getKey();
                for (int i = 0; i < values.size(); i++) {
                    rowData[i + 1] = values.get(i);
                }
                tableModel.addRow(rowData);
            }
        }
        return tableModel;
    }

    private  TableModel getjTableDefault() {
        String[] columnNames = {"Book ID", "Title","Book Author","Genre","ISBN","DOI","In Library"};
        Object[][] data ={
                {"No Record Found","No Record Found","No Record Found","No Record Found","No Record Found"
                        ,"No Record Found","No Record Found"}
        };
        return new DefaultTableModel(data, columnNames);
    }

    public void getLibraries(){
        ApiResponse<Map<String, Integer>> apiResponseLibrary = bookService.getAllLibraries();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseLibrary.getCode()){
            libraryMap = apiResponseLibrary.getData();
            libraryMap.forEach((s, i) -> searchLibrary.addItem(s));
        }
    }
    public void getGenres(){
        ApiResponse<Map<String, Integer>> apiResponseGenre = bookService.getAllGenres();
        if (ApiResponseCode.SUCCESS.getCode() == apiResponseGenre.getCode()){
            genresMap = apiResponseGenre.getData();
            genresMap.forEach((s, i) -> searchGenre.addItem(s));
        }
    }

    public boolean bookFound(BookDto bookDto) {
        bookService = BookServiceImpl.createInstance();
        bookService.bookFound(bookDto);
        return bookService.bookFound(bookDto).getData() != 0;
    }


    public static void main(String[] args) {
        BrowseLibrary browseLibrary = new BrowseLibrary();
    }
}
