package com.mvc;

import com.parking.core.models.entities.AccountGroup;
import com.parking.core.services.util.AccountGroupList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.Blog;
import com.parking.core.services.AccountService;
import com.parking.core.services.exceptions.AccountDoesNotExistException;
import com.parking.core.services.exceptions.AccountExistsException;
import com.parking.core.services.exceptions.BlogExistsException;
import com.parking.core.services.util.AccountList;
import com.parking.core.services.util.BlogList;
import com.rest.mvc.AccountController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    @Mock
    private UserDetails details;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountCaptor;

    Account antonio;
    Account mida;
    Account maradona;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        Mockito.when(details.getUsername()).thenReturn("antonio");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        accountCaptor = ArgumentCaptor.forClass(Account.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(details);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(details);

        antonio = new Account();
        antonio.setId(1L);
        antonio.setName("Antonio");
        antonio.setPassword("passwordAntonio");

        mida = new Account();
        mida.setId(2L);
        mida.setName("Mida");
        mida.setPassword("passwordMida");

        maradona = new Account();
        maradona.setId(3L);
        maradona.setName("Diego");
        maradona.setPassword("passwordDiego");
    }

    @Test
    public void findAllBlogsForAccount() throws Exception {
        List<Blog> list = new ArrayList<Blog>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(service.findBlogsByAccount(1L)).thenReturn(blogList);

        mockMvc.perform(get("/rest/accounts/1/blogs"))
                .andExpect(jsonPath("$.blogs[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Ignore
    @Test
    public void findAllAccountGroupsForAccount() throws Exception {
        List<AccountGroup> accountGroupList = new ArrayList<AccountGroup>();

        AccountGroup group1 = new AccountGroup();
        group1.setId(1L);
        group1.setGroupName("gruppo1");
        group1.setGroupDesc("gruppo1 description");
        group1.addAccount(antonio);
        group1.addAccount(mida);
        accountGroupList.add(group1);

        AccountGroup group2 = new AccountGroup();
        group2.setId(2L);
        group2.setGroupName("gruppo2");
        group1.setGroupDesc("gruppo2 description");
        accountGroupList.add(group2);

        AccountGroupList blogList = new AccountGroupList(accountGroupList);

        when(service.findAccountGroupsByAccount(1L)).thenReturn(blogList);

        mockMvc.perform(get("/rest/accounts/1/accountGroups"))
                .andExpect(jsonPath("$.groups[*].title",
                        hasItems(endsWith("Title A"), endsWith("Title B"))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void findAllBlogsForNonExistingAccount() throws Exception {
        List<Blog> list = new ArrayList<Blog>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");
        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(service.findBlogsByAccount(1L)).thenThrow(new AccountDoesNotExistException());

        mockMvc.perform(get("/rest/accounts/1/blogs"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBlogExistingAccount() throws Exception {
        Blog createdBlog = new Blog();
        createdBlog.setId(1L);
        createdBlog.setTitle("Test Title");

        when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(createdBlog);

        when(service.findByAccountName(anyString())).thenReturn(antonio);

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1"))))
                .andExpect(header().string("Location", endsWith("/blogs/1")))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void createBlogNonExistingAccount() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());

        Account antonio = new Account();
        antonio.setId(1L);
        when(service.findByAccountName(anyString())).thenReturn(antonio);

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBlogExistingBlogName() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

        Account antonio = new Account();
        antonio.setId(1L);
        when(service.findByAccountName(anyString())).thenReturn(antonio);

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setName("test");

        when(service.createAccount(any(Account.class))).thenReturn(createdAccount);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.name", is(createdAccount.getName())))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(service).createAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getPassword();
        assertEquals("test", password);
    }

    @Test
    public void createAccountExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(1L);
        createdAccount.setPassword("test");
        createdAccount.setName("test");

        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setPassword("test");
        foundAccount.setName("test");

        when(service.findAccount(1L)).thenReturn(foundAccount);

        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(jsonPath("$.name", is(foundAccount.getName())))
                .andExpect(jsonPath("$.links[*].rel",
                        hasItems(endsWith("self"), endsWith("blogs"))))
                .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingAccount() throws Exception {
        when(service.findAccount(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setName("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setName("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts"))
                .andExpect(jsonPath("$.accounts[*].name",
                        hasItems(endsWith("accountA"), endsWith("accountB"))))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void findAccountsByName() throws Exception {
        List<Account> accounts = new ArrayList<Account>();

        Account accountA = new Account();
        accountA.setId(1L);
        accountA.setPassword("accountA");
        accountA.setName("accountA");
        accounts.add(accountA);

        Account accountB = new Account();
        accountB.setId(1L);
        accountB.setPassword("accountB");
        accountB.setName("accountB");
        accounts.add(accountB);

        AccountList accountList = new AccountList(accounts);

        when(service.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/rest/accounts").param("name", "accountA"))
                .andExpect(jsonPath("$.accounts[*].name",
                        everyItem(endsWith("accountA"))))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
