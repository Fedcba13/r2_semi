package com.r2.movie.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.r2.movie.model.service.ReviewService;

/**
 * Servlet Filter implementation class InsertReviewFilter
 */
@WebFilter(servletNames = { "InsertReview" }, urlPatterns= {"/movie/insertReview.do"})
public class InsertReviewFilter implements Filter {

    public InsertReviewFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String memberId = request.getParameter("memberId");		
		String movieId = request.getParameter("movieId");
		
		
		boolean result = new ReviewService().isReviewed(memberId, movieId);
		
		if(result) {
			return ;
		} else {
			chain.doFilter(request, response);			
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
