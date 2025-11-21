package be.ipam.thepriceisright.dto.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "Paginated response wrapper")
public class PageResponse<T> {

    @Schema(description = "Items for this page")
    private List<T> content;

    @Schema(description = "Current page number", example = "0")
    private int page;

    @Schema(description = "Page size", example = "10")
    private int size;

    @Schema(description = "Total number of elements", example = "200")
    private long totalElements;

    @Schema(description = "Total number of pages", example = "20")
    private int totalPages;

    @Schema(description = "Is this the first page?", example = "true")
    private boolean first;

    @Schema(description = "Is this the last page?", example = "false")
    private boolean last;

    // Constructor based on Spring Data Page<T>
    public PageResponse(Page<T> pageObj) {
        this.content = pageObj.getContent();
        this.page = pageObj.getNumber();
        this.size = pageObj.getSize();
        this.totalElements = pageObj.getTotalElements();
        this.totalPages = pageObj.getTotalPages();
        this.first = pageObj.isFirst();
        this.last = pageObj.isLast();
    }

    // getters + setters
    public List<T> getContent() { return content; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isFirst() { return first; }
    public boolean isLast() { return last; }
}
