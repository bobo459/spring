package dw.wholesale_company.service;


import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    MileageRepository mileageRepository;
@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer saveCustomer(Customer customer){
    customerRepository.save(customer);
    return customer;
    }
    public List<Customer> getCustomerAll() {return customerRepository.findAll();}

    //고객 전체의 평균마일리지보다 평균마일리지가 큰 고객 정보/선생님답
/*    public List<Customer> getCustomerWithHighMileThanAvg() {
        List<Customer> customers = customerRepository.findAll();
        int sum = 0;
        for (int i=0; i<customers.size(); i++) {
            sum = sum + customers.get(i).getMileage();
        }
        Double avg = (double)sum / (double)customers.size();
        return customers.stream().filter(c->c.getMileage() > avg)
                .collect(Collectors.toList());
    }*/

    //6. 마일리지 등급명별로 고객수를 보이시오.
    public List<Customer> getCustomerByMileageGrade(String grade){
        List<Customer> customerList = customerRepository.findAll();
        List<Mileage> mileageList = mileageRepository.findAll();
        long d = 0;
        long c = 0;
        long b = 0;
        long a = 0;
        long s = 0;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getMileage()>0&& 99>customerList.get(i).getMileage()){
                d = d + 1;
            } else if (customerList.get(i).getMileage()>100&& 999>customerList.get(i).getMileage()) {
                c = c + 1;
            }else if (customerList.get(i).getMileage()>1000&& 9999>customerList.get(i).getMileage()) {
                b = b + 1;
            }else if (customerList.get(i).getMileage()>10000&& 99999>customerList.get(i).getMileage()) {
                a = a + 1;
            }else if (customerList.get(i).getMileage()>100000&& 999999>customerList.get(i).getMileage()) {
                s = s + 1;
            }
        }return customerList.stream().filter(m->mileageList.getClass().contains(grade)).collect(Collectors.toList());
    }

}
