using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.SqlClient;
using System.ServiceModel.Web;
using System.Runtime.Serialization;

using System.ServiceModel;

/* This is the WCF Service Library that the WCF Server will be built on
 * Brandon Gannicott
 * 10/18/12
 */ 

namespace StudentDataServiceLib
{
    public class StudentDataService : IStudentData
    {
        public StudentDataService()
        {
            Console.WriteLine("Ready to serve requests...");
        }  

        private SqlConnection connection;

         // Create an instance of an SqlConnection
        private SqlConnection connect()
        {
            connection = new SqlConnection();
            connection.ConnectionString =
                "Data Source=ARTURO;User ID=cdsMobile;Password=csh0rn3t;Initial Catalog=CDSMobile";
            return connection;
        }

        private string retrieveFromSQL(string SQLCmd)
        {
            string response = "";
            try
            {
                // Create an instance of SqlCommand.
                SqlCommand cmd = new SqlCommand();
                // Use the connection that we just opened.
                cmd.Connection = connection;
                // The command will be a text command.
                cmd.CommandType = CommandType.Text;
                // Specify the SQL text command
                cmd.CommandText = SQLCmd;

                // Execute the SQL command - the DataReader will be used to read the data
                SqlDataReader reader = cmd.ExecuteReader();

                // Write all the records in the "record set" out to the console
                while (reader.Read())
                {
                    response = reader.GetValue(0) + ", " + reader.GetValue(1);
                    Console.WriteLine(response); //debug purposes
                }
                reader.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine("{0}", e.Message);
            }
            return response;
        }
        void testReceiveXml(Message clientMsg)
        {
            Console.WriteLine("clientMsg received at" + DateTime.Now.ToString("h:mm:ss tt"));
            Console.WriteLine("clientMsg action:" + clientMsg.action);
            Console.WriteLine("clientMsg sessionId:" + clientMsg.sessionId);
            Console.WriteLine("clientMsg result:" + clientMsg.result);
            Console.WriteLine("clientMsg resultText:" + clientMsg.resultText);
        }
        Message testReceiveAndReturnXml(Message clientMsg)
        {
            testReceiveXml(clientMsg);
            return clientMsg;
        }
        public Message serve(Message clientMsg)
        {
            //this line works perfectly. Sweet.
            //return testReceiveAndReturnXml(clientMsg);
                        
            switch (clientMsg.action)
            {
                case "bio":
                    return bio(clientMsg);                    
                case "meals":
                    return meals(clientMsg);
                case "login":
                    return login(clientMsg);
                default:
                    return new Message();
            }
            
        }
        
        private Message bio(Message clientMsg)
        {
            //do something with the token to get the individualId and authenticate access
            //for now, just pass whatever I give it
            string individualId = clientMsg.sessionId;
            //craft a Response
            Message result = new Message();
            result.action = "bio";

            string response = "";
            string SQLCmd = "SELECT fullName, classification FROM mobileStudents WHERE individualID = '" + individualId + "'";
            connection = connect();

            try
            {
                connection.Open();
                response = retrieveFromSQL(SQLCmd);

            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes
            /*
            result.data.Add(response);
            result.data.Add("something else");
            result.data.Add("another something");
             * */
            result.result = 1;
            result.resultText = "Success";

            return result;
        }
        private Message meals(Message clientMsg)
        {
            return new Message();
        }
        private Message login(Message clientMsg)
        {
            return new Message();
        }
      
        /*
        public Response bio(Message clientMsg)
        {
            
            //do something with the token to get the individualId and authenticate access
            //for now, just pass whatever I give it
            string individualId = clientMsg.sessionId;
            //craft a Response
            Response result = new Response();
            result.action = "bio";

            string response = "";
            string SQLCmd = "SELECT fullName, classification FROM mobileStudents WHERE individualID = '" + individualId + "'";
            connection = connect();

            try
            {
                connection.Open();
                response = retrieveFromSQL(SQLCmd);

            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes

            result.data.Add(response);
            result.data.Add("something else");
            result.data.Add("another something");
            result.result = 1;
            result.resultText = "Success";

            return result;
        }
         * */      
        /*
        public string bio(string token){

            //do something with the token to get the individualId and authenticate access
            //for now, just pass whatever I give it
            string individualId = token;

            string response ="";
            string SQLCmd = "SELECT fullName, classification FROM mobileStudents WHERE individualID = '" + individualId + "'";
            connection = connect();
            
            try
            {
                connection.Open();
                response = retrieveFromSQL(SQLCmd);
            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes
            return response;          
        }
         */
        /*
        public Response bio(string token)
        {
            //do something with the token to get the individualId and authenticate access
            //for now, just pass whatever I give it
            string individualId = token;
            //craft a Response
            Response result = new Response();            
            result.action = "bio";            
            
            string response = "";
            string SQLCmd = "SELECT fullName, classification FROM mobileStudents WHERE individualID = '" + individualId + "'";
            connection = connect();

            try
            {
                connection.Open();
                response = retrieveFromSQL(SQLCmd);
                
            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes
           
            result.data.Add(response);
            result.data.Add("something else");
            result.data.Add("another something");
            result.result = 1;
            result.resultText = "Success";
            
            return result;        
        }
        */
        /*
        public Response bio(Response clientMsg)
        {
            //do something with the token to get the individualId and authenticate access
            //for now, just pass whatever I give it
            string individualId = clientMsg.sessionId;
            //craft a Response
            Response result = new Response();
            result.action = "bio";

            string response = "";
            string SQLCmd = "SELECT fullName, classification FROM mobileStudents WHERE individualID = '" + individualId + "'";
            connection = connect();

            try
            {
                connection.Open();
                response = retrieveFromSQL(SQLCmd);

            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes

            result.data.Add(response);
            result.data.Add("something else");
            result.data.Add("another something");
            result.result = 1;
            result.resultText = "Success";

            return result;
        }
         * */
        /*
        public string sqlRequest(string token, string command)
        {
            string response = "";
            //use the token for authentication somehow
            connection = connect();

            try
            {
                connection.Open();
                response = retrieveFromSQL(command);
            }
            catch (SqlException e)
            {
                response = e.Message;
            }

            connection.Close();
            Console.WriteLine("The connection to the database has been closed");//debug purposes
            return response;  
        }*/
    }
         
    [ServiceContract]
    public interface IStudentData
    {
        //This is a method that handles all input
        
        [OperationContract,XmlSerializerFormat]
        [WebInvoke(Method="POST", 
            RequestFormat=WebMessageFormat.Xml,
            ResponseFormat=WebMessageFormat.Xml,
            UriTemplate="serve")]
        Message serve(Message fromClient);
        
        /*
        [OperationContract]
        [WebGet(UriTemplate = "bio/{token}")]
        Response bio(string token);
        
        [OperationContract]        
        Response bio(Response appMsg);

        [OperationContract]
        [WebGet(UriTemplate = "sql/{token}&{command}")]
        string sqlRequest(string token, string command);
        */
    }

}
