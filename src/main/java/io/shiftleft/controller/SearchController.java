package io.shiftleft.controller;

import io.shiftleft.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ParserContext;
import org.springframework.binding.expression.beanwrapper.BeanWrapperExpressionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.binding.expression.support.FluentParserContext;
import org.springframework.binding.expression.support.StaticExpression;
import org.springframework.binding.mapping.MappingResult;

/**
 * Search login
 */
@Controller
public class SearchController {

  @RequestMapping(value = "/search/user", method = RequestMethod.GET)
  public String doGetSearch(@RequestParam String foo, HttpServletResponse response, HttpServletRequest request) {
    java.lang.Object message = new Object();
    try {
      ParserContext parserContext = new FluentParserContext().evaluate(Customer.class);
      BeanWrapperExpressionParser parser = new BeanWrapperExpressionParser();
      Expression exp = parser.parseExpression(foo, parserContext);
      message = (Object) exp.getValueType(new Customer());
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    return message.toString();
  }
}
