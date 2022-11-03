package controller;
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.ResultUtil;
import domain.board.BoardDto;
import form.BoardForm;
import service.BoardService;
 
@Controller
@RequestMapping(value = "/board")
public class BoardController {
 
    @Autowired
    private BoardService boardService;
 

    @RequestMapping(value = "/boardList")
    public String boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        return "board/boardList";
    }
 

    @RequestMapping(value = "/getBoardList")
    @ResponseBody
    public ResultUtil getBoardList(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
        ResultUtil resultUtils = boardService.getBoardList(boardForm);
 
        return resultUtils;
    }
 

    @RequestMapping(value = "/boardDetail")
    public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        return "board/boardDetail";
    }
 

    @RequestMapping(value = "/getBoardDetail")
    @ResponseBody
    public BoardDto getBoardDetail(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
    	MDC.put("ID", String.valueOf(boardForm.getBoard_seq()));
    	
        BoardDto boardDto = boardService.getBoardDetail(boardForm);
        
        MDC.remove("ID");
 
        return boardDto;
    }
 

    @RequestMapping(value = "/boardWrite")
    public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        return "board/boardWrite";
    }
 

    @RequestMapping(value = "/insertBoard")
    @ResponseBody
    public BoardDto insertBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
                    
        BoardDto boardDto = boardService.insertBoard(boardForm);
 
        return boardDto;
    }
 

    @RequestMapping(value = "/deleteBoard")
    @ResponseBody
    public BoardDto deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
        BoardDto boardDto = boardService.deleteBoard(boardForm);
 
        return boardDto;
    }
 

    @RequestMapping(value = "/boardUpdate")
    public String boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        return "board/boardUpdate";
    }
 

    @RequestMapping(value = "/updateBoard")
    @ResponseBody
    public BoardDto updateBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
        BoardDto boardDto = boardService.updateBoard(boardForm);
 
        return boardDto;
    }
 

    @RequestMapping(value = "/boardReply")
    public String boardReply(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        return "board/boardReply";
    }
 

    @RequestMapping(value = "/insertBoardReply")
    @ResponseBody
    public BoardDto insertBoardReply(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
        BoardDto boardDto = boardService.insertBoardReply(boardForm);
 
        return boardDto;
    }
    

    @RequestMapping("/fileDownload")                      
    public ModelAndView fileDownload(@RequestParam("fileNameKey") String fileNameKey
                                    ,@RequestParam("fileName") String fileName
                                    ,@RequestParam("filePath") String filePath) throws Exception {
          

        Map<String, Object> fileInfo = new HashMap<String, Object>();
        fileInfo.put("fileNameKey", fileNameKey);
        fileInfo.put("fileName", fileName);
        fileInfo.put("filePath", filePath);
     
        return new ModelAndView("fileDownloadUtil", "fileInfo", fileInfo);
    }
}
