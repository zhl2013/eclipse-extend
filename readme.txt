为eclipse 制定自己的注释模板。
	
	参考：http://shareal.blog.163.com/blog/static/27659056201193063914196/
	
	1、找到eclipse 当前版本中的相关jar
	org.eclipse.jdt.core_3.7.3.v20120119-1537.jar
	org.eclipse.jdt.ui_3.7.2.v20120109-1427.jar
	org.eclipse.jface.text_3.7.2.v20111213-1208.jar
	org.eclipse.text_3.5.101.v20110928-1504.jar
	
	2、反编译类
	org.eclipse.jdt.internal.corext.template.java.CodeTemplateContextType
	注意内部类
	
	3、添加自定义模板 在CodeTemplateContextType 添加内部类
	
		public static class Copyright extends TemplateVariableResolver{
			private static final String COPYRIGHT = System.getProperty("copyright");
			public Copyright() {
				super("copyright","Copyright all resolved");
			}
			
			@Override
			protected String resolve(TemplateContext context) {
				return COPYRIGHT;
			}
		}
	
	4、在构造中注册自定义模板
	
		public CodeTemplateContextType(String contextName) {
		super(contextName);
		...
		addResolver(new CodeTemplateContextType.Todo());
		addResolver(new CodeTemplateContextType.Copyright());
		...
		}
	5、修改eclipse_home/eclipse.ini 添加定义的参数
	-vmargs
	-Dosgi.requiredJavaVersion=1.5
	-Xms40m
	-Xmx512m
	-Duser.name={xxxx@qq.com}
	-Dcopyright=XXX
	
	6、注意编译class版本，不要最高的，这里用1.6，用rar，打开org.eclipse.jdt.ui_3.7.2.v20120109-1427.jar 
	找到org.eclipse.jdt.internal.corext.template.java.CodeTemplateContextType 目录，把编译的class 覆盖
	copy 到 eclise_home/plugins 下，重启eclipse，修改window--preferences--java---code style--code template 添加  ${copyright}
	
	eclipse会有 copyright 注释模板参数
	
	