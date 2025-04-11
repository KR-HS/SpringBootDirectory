package com.coding404.myweb.product.service;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.mapper.ProductMapper;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service("productService") // 멤버변수명과 동일한 이름
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Value("${com.coding404.myweb.upload.path}")
    private String uploadPath;

    // 폴더생성함수
    private String makeFolder(){
        String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File file = new File(uploadPath +"/"+ filepath);
        if(file.exists()==false){ // 해당위치에 파일 or 폴더가 존재하지 않으면 if문 작동
            file.mkdirs();
        }
        return filepath;
    }

    // 트랜잭션 : 멀티 insert환경에서 예외 발생시에 이전데이터는 남아있게 되므로, 데이터 불일치 발생
    //@Transactional - 이 메서드를 하나의 트랜잭션으로 관리함
    @Transactional(rollbackFor = Exception.class) // 에러가 터지면 롤백( try-catch로 에러처리가 되면 롤백 x)
    @Override
    public boolean productRegist(ProductVO vo, List<MultipartFile> files) {
        // 상품 인서트
        boolean result  = productMapper.productRegist(vo);

        // 2. 업로드
        for(MultipartFile file:files){
            String originName = file.getOriginalFilename();
            String filename = originName.substring(originName.lastIndexOf("/") + 1);
            UUID uuid = UUID.randomUUID(); // 16진수형태의 랜덤문자열을 반환
            String filepath = makeFolder();

            String path = uploadPath +"/"+ filepath + "/" +uuid + "_" + filename ; // 업로드패스

            try {

                File saveFile = new File(path);
                file.transferTo(saveFile); // 파일업로드를 처리
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 2. upload테이블에 경로를 저장
            // selectKey, generateKey방식을 이용해서 키값을 구해옴
            // selectKey : 인서트 넣기 전에 사용자가 지정한 select쿼리를 날릴 수 있는 구문
            // generateKey - 인서트 넣기전에 인서트한
            result = productMapper.productRegistFile(ProductUploadVO.
                    builder().
                    filename(filename).
                    filepath(filepath).
                    uuid(uuid.toString()).
                    prodId(vo.getProdId()).
                    prodWriter(vo.getProdWriter()).
                    build());

        } // end for

        return result;
    }

    @Override
    public List<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter,cri);
    }

    @Override
    public int getTotal(String prodWriter,Criteria cri) {
        return productMapper.getTotal(prodWriter,cri);
    }

    @Override
    public ProductVO getDetail(int prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public boolean productUpdate(ProductVO vo) {
        return productMapper.productUpdate(vo);
    }

    @Override
    public boolean productDelete(int prodId) {
        return productMapper.productDelete(prodId);
    }

    @Override
    public List<CategoryVO> getCategory() {
        return productMapper.getCategory();
    }

    @Override
    public List<CategoryVO> getCategorySub(CategoryVO vo) {
        return productMapper.getCategorySub(vo);
    }

    @Override
    public List<ProductUploadVO> getDetailImg(String prodId) {
        return productMapper.getDetailImg(prodId);
    }
}
