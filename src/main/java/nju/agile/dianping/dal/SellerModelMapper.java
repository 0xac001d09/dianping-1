package nju.agile.dianping.dal;

import nju.agile.dianping.model.SellerModel;

import java.util.List;

public interface SellerModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    List<SellerModel> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    int insert(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    int insertSelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    SellerModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    int updateByPrimaryKeySelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Fri Dec 13 22:34:40 CST 2019
     */
    int updateByPrimaryKey(SellerModel record);

    Integer countAllSeller();
}