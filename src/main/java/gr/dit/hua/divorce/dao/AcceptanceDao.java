package gr.dit.hua.divorce.dao;


import gr.dit.hua.divorce.entity.Acceptance;

public interface AcceptanceDao {

        void save(Acceptance acceptance);

        Acceptance findByTaxNumber(Integer tax_number);

        void deleteByTaxNumber(Integer tax_number);

}
