
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 851649
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        //validate that action is initiated properly. 
        if(action != null){
            if (action.equals("logout")){
            session.invalidate();
            }
        }
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ArrayList<String> shoppingCart ;
        
        //check if there is a preexisting shopping cart
        if(session.getAttribute("session_shoppingList") != null){
        shoppingCart = (ArrayList<String>)session.getAttribute("session_shoppingList");
        }
        else{
        shoppingCart = new ArrayList<>();
        }
        
        //validate that action is initiated properly. 
        if(action != null){    
        switch(action){
                case "register":
                    session.setAttribute("username", request.getParameter("username"));
                    request.setAttribute("username", session.getAttribute("username"));
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    break;
                case "add":
                    shoppingCart.add(request.getParameter("item"));
                    session.setAttribute("session_shoppingList", shoppingCart);
                    request.setAttribute("cart",shoppingCart);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    break;
                case "delete":
                    //code
                    break;
                case "logout":
                    doGet(request,response);
                    break;
                default:
                    //code
                    break;
                
            }
        }
    }

}
