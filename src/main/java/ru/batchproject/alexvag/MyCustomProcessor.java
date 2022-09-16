package ru.batchproject.alexvag;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCustomProcessor implements ItemProcessor<User, MongoUser> {

  @Override
  public MongoUser process(User user) throws Exception {
    System.out.println("MyBatchProcessor : Processing data : "+user);
    MongoUser mongoUser = new MongoUser();
    //manager.setId(emp.getId());
    mongoUser.setName(user.getName().toUpperCase());
    return mongoUser;
  }
}