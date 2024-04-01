package errorparser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorModel {
    public String packageName;
    public String lastError;
    public boolean NeedInstall;
    public String ObjectName; /*  В чём ошибка, данные или скрипт или Клиентский модуль  */
}
