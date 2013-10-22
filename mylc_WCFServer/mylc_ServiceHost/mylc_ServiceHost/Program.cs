using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using StudentDataServiceLib;

namespace mylc_ServiceHost
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("**** Console based WCF StudentDataService Host (for Android) ****");

            using (ServiceHost servicehost = new ServiceHost(typeof(StudentDataService)))
            {
                //Open host and start listening for incoming messages
                servicehost.Open();
                DisplayHostInfo(servicehost);

                //Keep service running until the Enter key is pressed
                Console.WriteLine("The service is ready.");
                Console.WriteLine("Press Enter to terminate the service.");
                Console.ReadLine();
            }
        }
        static void DisplayHostInfo(ServiceHost host)
        {
            Console.WriteLine();
            Console.WriteLine("***** Host Info *****");

            foreach (System.ServiceModel.Description.ServiceEndpoint se in host.Description.Endpoints)
            {
                Console.WriteLine("Address: {0}", se.Address);
                Console.WriteLine("Binding: {0}", se.Binding.Name);
                Console.WriteLine("Contract: {0}", se.Contract.Name);
                Console.WriteLine();
            }
            Console.WriteLine("****************************");
        }
    }
}