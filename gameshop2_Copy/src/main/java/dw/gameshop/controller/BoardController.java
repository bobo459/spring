package dw.gameshop.controller;

import dw.gameshop.dto.BaseResponse;
import dw.gameshop.dto.BoardDto;
import dw.gameshop.enumstatus.ResultCode;
import dw.gameshop.model.Board;
import dw.gameshop.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<BaseResponse<List<BoardDto>>> getAllBoards() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        boardService.getAllBoards(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<BaseResponse<Board>> saveBoard(@RequestBody Board board) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        boardService.saveBoard(board),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.CREATED);
    }

    @PostMapping("/board/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteBoard(@PathVariable long id) {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        boardService.deleteBoard(id),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }
}