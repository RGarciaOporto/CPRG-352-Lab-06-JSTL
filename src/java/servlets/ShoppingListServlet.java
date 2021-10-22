
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
        if (action != null && action.equals("logout")){
            session.invalidate();
            request.setAttribute("message", "You have been successfully logged out.");
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
                    //only register users when they add a username
                    String username = request.getParameter("username");
                    if(username.length() > 0){
                    session.setAttribute("username", request.getParameter("username"));
                    request.setAttribute("username", session.getAttribute("username"));
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    }
                    else{
                    request.setAttribute("message", "Please enter a username.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
                    }
                    break;
                case "add":
                    //only add an item if one has been written in the jsp. 
                    String newItem = request.getParameter("item");
                    if(newItem.length() > 0){
                    shoppingCart.add(request.getParameter("item"));
                    session.setAttribute("session_shoppingList", shoppingCart);
                    request.setAttribute("cart", shoppingCart);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    } 
                    else{
                    request.setAttribute("message", "Please enter an item before adding");
                    request.setAttribute("cart", shoppingCart);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    }
                    break;
                case "delete":
                    String removeItem = request.getParameter("item");
                    //only go through deletion process if an item has been selected.
                    if(removeItem != null){
                    for(int i = 0; i < shoppingCart.size(); i++){
                    if(shoppingCart.get(i).equals(removeItem))
                        shoppingCart.remove(i);
                    }
                    session.setAttribute("session_shoppingList", shoppingCart);
                    request.setAttribute("cart", shoppingCart);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    }
                    else{
                    request.setAttribute("message", "Please select an item to delete.");
                    request.setAttribute("cart", shoppingCart);
                    getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
                    }
                    break;
                case "logout":
                    doGet(request,response);
                    break;
            }
        }
    }

}
