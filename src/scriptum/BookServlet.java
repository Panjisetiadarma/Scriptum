package scriptum;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;
    
    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        
        if (keyword != null && !keyword.isEmpty()) {
            List<Buku> books = bookDAO.searchBooks(keyword);
            request.setAttribute("books", books);
        } else if (action == null || action.equals("list")) {
            List<Buku> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("/addBook.jsp").forward(request, response);
            return;
        } else if (action.equals("edit")) {
            String id = request.getParameter("id");
            if (id != null) {
                Buku buku = bookDAO.getBookById(Integer.parseInt(id));
                request.setAttribute("buku", buku);
                request.getRequestDispatcher("/editBook.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            String id = request.getParameter("id");
            if (id != null) {
                bookDAO.deleteBook(Integer.parseInt(id));
            }
            response.sendRedirect(request.getContextPath() + "/books");
            return;
        }
        
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            Buku buku = new Buku();
            buku.setJudul(request.getParameter("judul"));
            buku.setPenulis(request.getParameter("penulis"));
            buku.setPenerbit(request.getParameter("penerbit"));
            buku.setThnTerbit(Integer.parseInt(request.getParameter("thnTerbit")));
            buku.setKtegori(request.getParameter("ktegori"));
            buku.setStok(Integer.parseInt(request.getParameter("stok")));
            
            bookDAO.addBook(buku);
        } else if (action.equals("update")) {
            Buku buku = new Buku();
            buku.setBukuId(Integer.parseInt(request.getParameter("bukuId")));
            buku.setJudul(request.getParameter("judul"));
            buku.setPenulis(request.getParameter("penulis"));
            buku.setPenerbit(request.getParameter("penerbit"));
            buku.setThnTerbit(Integer.parseInt(request.getParameter("thnTerbit")));
            buku.setKtegori(request.getParameter("ktegori"));
            buku.setStok(Integer.parseInt(request.getParameter("stok")));
            
            bookDAO.updateBook(buku);
        }
        
        response.sendRedirect(request.getContextPath() + "/books");
    }
}

