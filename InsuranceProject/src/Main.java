import model.*;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        AgencyService agencyService = new AgencyService();
        BankAccountService bankAccountService = new BankAccountService();
        InsuranceCompanyService insuranceCompanyService = new InsuranceCompanyService();
        InsuranceService insuranceService = new InsuranceService();
        CustomerService customerService = new CustomerService();
        PolicyService policyService = new PolicyService();
        VehicleService vehicleService = new VehicleService();
        AccidentService accidentService = new AccidentService();
        InsuranceRequestService insuranceRequestService = new InsuranceRequestService();
        ProposalService proposalService = new ProposalService();

        BankAccount customerBankAccount = bankAccountService.createBankAccount("Ziraat", "TR7364872374983",
                new BigDecimal(10000));
        BankAccount agencyBankAccount = bankAccountService.createBankAccount("Yapı Kredi", "TR48777433",
                new BigDecimal(12000));
        BankAccount insuranceCompanyBankAccount = bankAccountService.createBankAccount("Yapı Kredi", "TR490680595",
                new BigDecimal(20000));

        Agency agency = agencyService.createAgency("Aaaa");

        Insurance insurance = insuranceService.createInsurance(InsuranceTypeEnum.COMPULSORY_TRAFFIC_INSURANCE,
                "Traffic Insurance");
        InsuranceCompany insuranceCompany = insuranceCompanyService.createInsuranceCompany("Allianz Sigorta",
                "23245435", "XX", "Ataşehir/İstanbul", new BigDecimal(0.10));

        Customer customer = customerService.createCustomer("Egemen Kaya", CustomerTypeEnum.INDIVIDUAL);

        LocalDate accidentDate = LocalDate.of(2023, Month.JULY, 12);
        Accident accident = accidentService.createAccident(java.sql.Date.valueOf(accidentDate),"Kaza meydana geldi,",
                new BigDecimal(5000.00), 6);

        Vehicle vehicle = vehicleService.createVehicle("Sedan", "14 ABC 123", "XYZ123456789",
                "Toyota", 2022, ColorTypeEnum.BLACK);

        LocalDate startDate = LocalDate.of(2023, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2024, Month.JULY, 1);
        Policy policy = policyService.createPolicy(insuranceCompany, vehicle, new BigDecimal(900000),
                java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));

        InsuranceRequest insuranceRequest = insuranceRequestService.createInsuranceRequest(vehicle, policy);

        LocalDate startDate1 = LocalDate.of(2023, Month.JULY, 1);
        LocalDate endDate1 = LocalDate.of(2024, Month.JULY, 1);
        LocalDate expireDate = startDate.plusDays(3);
        Proposal proposal = proposalService.createProposal(insuranceCompany, vehicle, new BigDecimal(1200),
                java.sql.Date.valueOf(startDate1), java.sql.Date.valueOf(endDate1), java.sql.Date.valueOf(expireDate),
                true, new BigDecimal(100));

        insuranceRequestService.addProposalToInsuranceRequest(proposal, insuranceRequest);


        vehicleService.addAccidentToVehicle(accident, vehicle);

        insuranceCompanyService.addInsuranceToInsuranceCompany(insurance, insuranceCompany);
        insuranceCompanyService.addBankAccountToInsuranceCompany(insuranceCompanyBankAccount, insuranceCompany);

        agencyService.addInsuranceCompanyToAgency(agency, insuranceCompany);
        agencyService.addBankAccountToAgency(agency,agencyBankAccount);
        agencyService.addCustomerToAgency(customer, agency);

        customerService.addVehicleToCustomer(vehicle, customer);
        customerService.addBankAccountToCustomer(customerBankAccount, customer);
        customerService.addPolicyToCustomer(policy, customer);
        customerService.addInsuranceRequestToCustomer(customer, insuranceRequest);

        //Homework
        customerService.acceptProposal(customer, proposal, insuranceRequest, agency, insuranceCompany);

        //System.out.println(agency.toString());
        System.out.println(customer.getPaymentMovementList());
        System.out.println(agency.getPaymentMovementList());
        System.out.println(insuranceCompany.getPaymentMovementList());
    }
}