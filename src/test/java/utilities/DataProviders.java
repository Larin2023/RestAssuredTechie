package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

// the First Data  provider method is returning all the details for
//the user
    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";         // this method navigates to Userdata.xlsx file to use the data
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");
        int colcount=xl.getCellCount("Sheet1",1);

        String apidata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                apidata[i-1][j]= xl.getCellData("Sheet1",i, j);
            }
        }

        return apidata;
    }

// the second data provider method  is returning only users or only user names

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Sheet1");

        String apidata[]=new String[rownum];

        for(int i=1;i<=rownum;i++)
        {
            apidata[i-1]= xl.getCellData("Sheet1",i, 1);

        }

        return apidata;
    }

}