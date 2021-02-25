package akbal.berkan.app.controller;

import akbal.berkan.app.entity.Admin;
import akbal.berkan.app.entity.Task;
import akbal.berkan.app.entity.User;
import akbal.berkan.app.repository.ITaskRepository;
import akbal.berkan.app.repository.IUserRepository;
import akbal.berkan.app.service.IAdminService;
import akbal.berkan.app.service.ITaskService;
import akbal.berkan.app.service.IUserService;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserPanelController {

    private final ITaskService m_taskService;
    private final IUserService m_userService;
    private final IAdminService m_adminService;

    public UserPanelController(ITaskService m_taskService, IUserService m_userService, IAdminService m_adminService) {
        this.m_taskService = m_taskService;
        this.m_userService = m_userService;
        this.m_adminService = m_adminService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model)
    {
        User user = new User();
        model.addAttribute(user);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user, ModelAndView mav)
    {

            m_userService.save(user);
            mav.addObject("user", user);
            mav.setViewName("home");
            return mav;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model)
    {
        Admin admin = new Admin();
        model.addAttribute(admin);

        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView saveAdmin(@ModelAttribute Admin admin, ModelAndView mav)
    {
        m_adminService.saveAdmin(admin);

        mav.addObject("taskList", m_taskService.findAll());
        mav.setViewName("list");

        return mav;
    }


    @GetMapping("/home")
    public String getHomePage(Model model)
    {
        Task task = new Task();
        model.addAttribute(task);
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        User user = new User();
        model.addAttribute(user);

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView getLogin(@ModelAttribute User user, ModelAndView mav)
    {
        User record = m_userService.findByEmail(user.email).get();

        if (record.getPassword().equals(user.getPassword()))
        {
            mav.addObject("user", user);
            mav.addObject("taskList", record.getTaskList());

            mav.setViewName("home");
            return mav;
        }

        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/add/{email}")
    public String getTaskPage(Model model, @PathVariable("email") String email)
    {
        Task task = new Task();
        model.addAttribute(task);
        model.addAttribute("email", email);
        return "add";
    }

    @PostMapping("/add/{email}")
    public ModelAndView addTask(@ModelAttribute Task task, @PathVariable("email") String email, ModelAndView mav)
    {
        User user = m_userService.findByEmail(email).get();
        task.setUser(user);
        m_taskService.save(task);

        mav.addObject("taskList",user.getTaskList());
        mav.addObject("user", user);
        mav.setViewName("home");

        return mav;

    }

        @GetMapping("/edit/{id}")
        public ModelAndView editTask(@PathVariable("id") int id, ModelAndView mav)
        {
            Task task = m_taskService.findById(id).get();
            mav.addObject("task", task);

            mav.addObject("email", task.getUser().getEmail());
            mav.setViewName("edit");

            return mav;
        }



}
