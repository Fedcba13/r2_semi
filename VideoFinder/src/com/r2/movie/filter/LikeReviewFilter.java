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
 * Servlet Filter implementation class LikeReviewFilter
 */
@WebFilter(servletNames = {"LikeReviewServlet","DislikeReviewServlet"}, urlPatterns={ "/movie/likeReview.do", "/movie/dislikeReview.do" })
public class LikeReviewFilter implements Filter {

    public LikeReviewFilter() {
     
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String memberId = request.getParameter("memberId");
		String reviewNo = request.getParameter("rn");
		
		boolean result = new ReviewService().isLiked(reviewNo, memberId);
		
		if(result) {
			return ;
		} else {
			chain.doFilter(request, response);
		}	
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
