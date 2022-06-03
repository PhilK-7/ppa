package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.philk7.ppaprojectapp.DataBinderMapperImpl());
  }
}
