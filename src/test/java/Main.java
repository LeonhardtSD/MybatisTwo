import com.gaozhaoxi.db.DBUtils;
import com.gaozhaoxi.entity.User;
import com.gaozhaoxi.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Leon
 */
public class Main {
    @Test
    public void test1(){
        SqlSession sqlSession=null;
        try{
            sqlSession= DBUtils.openSqlSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            User user=userMapper.getUser(2L);
            System.out.println(user.toString());
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test2(){
        SqlSession sqlSession=null;
        try{
            sqlSession=DBUtils.openSqlSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            for(int i=1;i<3;i++){
                userMapper.insertUser(new User(null,"u-"+i,"p+"+i,"a-"+i));
                System.out.println(this.toString());
            }
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
    public void test3(){
        SqlSession sqlSession=null;
        try{
            sqlSession=DBUtils.openSqlSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

            int i = userMapper.deleteUser(4L);
            System.out.print("删除是否成功"+i);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }


    }
}
