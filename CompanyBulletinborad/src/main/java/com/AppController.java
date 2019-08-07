package com;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.User;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" }, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT, RequestMethod.DELETE })

@RequestMapping("posts")

public class AppController {

	@Autowired
	CompanyPostsRepository cprRepo;
	@Autowired
	CommentRepository cmntRepo;

	@Autowired
	private UserRepository userRepo;

	@PostMapping("/signup")
	public User signUp(HttpServletRequest req, @RequestBody User user) {

		User result = null;
		try {
			result = userRepo.save(user);
			
			if (result == null)
				return null;

			HttpSession session = req.getSession(true);
			session.setAttribute("username", result.getUsername());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/signin")
	public Status signIn(HttpServletRequest req, @RequestBody User user) {
		User temp = userRepo.findByUsername(user.getUsername());

			if (temp == null)
			return new Status(false);

		if (temp.getPassword().equals(user.getPassword())) {
			HttpSession session = req.getSession(true);
			session.setAttribute("username", temp.getUsername());
			return new Status(true);
		}
		return new Status(false);
	}

	@PostMapping("/signout")
	public Status logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);

		if (session != null && session.getAttribute("username") != null) {
			session.invalidate();
			return new Status(true);
		}
		return new Status(false);
	}

	@PostMapping("/post/save")
	public CompanyPost savePost(HttpServletRequest req,@RequestBody CompanyPost post) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		post.setUser(new User());
		post.getUser().setId(post.getUfk());
		return cprRepo.save(post);
	}

	@PutMapping("/edit")
	public CompanyPost editPost(HttpServletRequest req, @RequestBody CompanyPost post) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		post = cprRepo.save(post);
		return post;
	}

	@GetMapping("/all")
	public List<CompanyPost> getCompanyPosts(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		List<CompanyPost> posts = new ArrayList<CompanyPost>();
		Iterable<CompanyPost> iterable = cprRepo.findAll();
		for (CompanyPost companypost : iterable) {
			posts.add(companypost);
		}
		return posts;

	}

	@DeleteMapping("/delete/{pid}")
	public Status deleteCompanyPosts(HttpServletRequest req, @PathVariable Integer pid) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		cprRepo.deleteById(pid);
		return new Status(true);
	}

	@PostMapping("/comment/save")
	public Comment saveComment(HttpServletRequest req,@RequestBody Comment comment) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		comment.setPost((CompanyPost) new CompanyPost());
		comment.getPost().setId(comment.getFk());
		return cmntRepo.save(comment);
	}

	@PutMapping("/comment/edit")
	public Comment editComment(HttpServletRequest req, @RequestBody Comment comment) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		comment.setPost(new CompanyPost());
		comment.getPost().setId(comment.getFk());
		comment = cmntRepo.save(comment);
		return comment;
	}

	@GetMapping("/comments/all")
	public List<Comment> getCompanyComments(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		List<Comment> comments = new ArrayList<Comment>();
		Iterable<Comment> iterable = cmntRepo.findAll();
		for (Comment comment : iterable) {
			comments.add(comment);
		}
		return comments;

	}

	@DeleteMapping("comment/delete/{commentId}")

	public Status deleteComment(HttpServletRequest req, @PathVariable Integer commentId) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		cmntRepo.deleteById(commentId);
		return new Status(true);
	}

	@GetMapping("/user/all")
	public List<User> getUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		List<User> users = new ArrayList<User>();
		Iterable<User> iterable = userRepo.findAll();
		for (User user : iterable) {
			users.add(user);
		}
		return users;

	}

	@GetMapping("/info/{username}")
	public List<CompanyPost> getPostsByUsername(HttpServletRequest req, @PathVariable String username) {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null)
			return null;
		User user = userRepo.findByUsername(username);
		System.out.println(user);
		List<CompanyPost> obj =cprRepo.findByUser(user);
		System.out.println(obj);
		return obj;
	

	}

		
	
}