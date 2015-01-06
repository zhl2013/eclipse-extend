/*  
 * @(#) CodeTemplateContextType.java Create on 2015-1-6 上午10:39:03   
 *   
 * Copyright 2015 by yhx.   
 */

package org.eclipse.jdt.internal.corext.template.java;

import java.util.ArrayList;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.dom.TokenScanner;
import org.eclipse.jdt.internal.corext.util.Messages;
import org.eclipse.jdt.internal.ui.viewsupport.BasicElementLabels;
import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

/**
 * @CodeTemplateContextType.java
 * @created at 2015-1-6 上午10:39:03 by {253587517@qq.com}
 * 
 * @desc
 * 
 * @author {253587517@qq.com}
 * @version $Revision$
 * @update: $Date$
 */
public class CodeTemplateContextType extends TemplateContextType {

	public static final String CATCHBLOCK_CONTEXTTYPE = "catchblock_context";
	public static final String METHODBODY_CONTEXTTYPE = "methodbody_context";
	public static final String CONSTRUCTORBODY_CONTEXTTYPE = "constructorbody_context";
	public static final String GETTERBODY_CONTEXTTYPE = "getterbody_context";
	public static final String SETTERBODY_CONTEXTTYPE = "setterbody_context";
	public static final String NEWTYPE_CONTEXTTYPE = "newtype_context";
	public static final String CLASSBODY_CONTEXTTYPE = "classbody_context";
	public static final String INTERFACEBODY_CONTEXTTYPE = "interfacebody_context";
	public static final String ENUMBODY_CONTEXTTYPE = "enumbody_context";
	public static final String ANNOTATIONBODY_CONTEXTTYPE = "annotationbody_context";
	public static final String FILECOMMENT_CONTEXTTYPE = "filecomment_context";
	public static final String TYPECOMMENT_CONTEXTTYPE = "typecomment_context";
	public static final String FIELDCOMMENT_CONTEXTTYPE = "fieldcomment_context";
	public static final String METHODCOMMENT_CONTEXTTYPE = "methodcomment_context";
	public static final String CONSTRUCTORCOMMENT_CONTEXTTYPE = "constructorcomment_context";
	public static final String OVERRIDECOMMENT_CONTEXTTYPE = "overridecomment_context";
	public static final String DELEGATECOMMENT_CONTEXTTYPE = "delegatecomment_context";
	public static final String GETTERCOMMENT_CONTEXTTYPE = "gettercomment_context";
	public static final String SETTERCOMMENT_CONTEXTTYPE = "settercomment_context";
	private static final String CODETEMPLATES_PREFIX = "org.eclipse.jdt.ui.text.codetemplates.";
	public static final String COMMENT_SUFFIX = "comment";
	public static final String CATCHBLOCK_ID = "org.eclipse.jdt.ui.text.codetemplates.catchblock";
	public static final String METHODSTUB_ID = "org.eclipse.jdt.ui.text.codetemplates.methodbody";
	public static final String NEWTYPE_ID = "org.eclipse.jdt.ui.text.codetemplates.newtype";
	public static final String CONSTRUCTORSTUB_ID = "org.eclipse.jdt.ui.text.codetemplates.constructorbody";
	public static final String GETTERSTUB_ID = "org.eclipse.jdt.ui.text.codetemplates.getterbody";
	public static final String SETTERSTUB_ID = "org.eclipse.jdt.ui.text.codetemplates.setterbody";
	public static final String FILECOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.filecomment";
	public static final String TYPECOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.typecomment";
	public static final String CLASSBODY_ID = "org.eclipse.jdt.ui.text.codetemplates.classbody";
	public static final String INTERFACEBODY_ID = "org.eclipse.jdt.ui.text.codetemplates.interfacebody";
	public static final String ENUMBODY_ID = "org.eclipse.jdt.ui.text.codetemplates.enumbody";
	public static final String ANNOTATIONBODY_ID = "org.eclipse.jdt.ui.text.codetemplates.annotationbody";
	public static final String FIELDCOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.fieldcomment";
	public static final String METHODCOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.methodcomment";
	public static final String CONSTRUCTORCOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.constructorcomment";
	public static final String OVERRIDECOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.overridecomment";
	public static final String DELEGATECOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.delegatecomment";
	public static final String GETTERCOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.gettercomment";
	public static final String SETTERCOMMENT_ID = "org.eclipse.jdt.ui.text.codetemplates.settercomment";
	public static final String EXCEPTION_TYPE = "exception_type";
	public static final String EXCEPTION_VAR = "exception_var";
	public static final String ENCLOSING_METHOD = "enclosing_method";
	public static final String ENCLOSING_TYPE = "enclosing_type";
	public static final String BODY_STATEMENT = "body_statement";
	public static final String FIELD = "field";
	public static final String FIELD_TYPE = "field_type";
	public static final String BARE_FIELD_NAME = "bare_field_name";
	public static final String PARAM = "param";
	public static final String RETURN_TYPE = "return_type";
	public static final String SEE_TO_OVERRIDDEN_TAG = "see_to_overridden";
	public static final String SEE_TO_TARGET_TAG = "see_to_target";
	public static final String TAGS = "tags";
	public static final String TYPENAME = "type_name";
	public static final String FILENAME = "file_name";
	public static final String PACKAGENAME = "package_name";
	public static final String PROJECTNAME = "project_name";
	public static final String PACKAGE_DECLARATION = "package_declaration";
	public static final String TYPE_DECLARATION = "type_declaration";
	public static final String CLASS_BODY = "classbody";
	public static final String INTERFACE_BODY = "interfacebody";
	public static final String ENUM_BODY = "enumbody";
	public static final String ANNOTATION_BODY = "annotationbody";
	public static final String TYPE_COMMENT = "typecomment";
	public static final String FILE_COMMENT = "filecomment";
	private boolean fIsComment;

	public CodeTemplateContextType(String contextName) {
		super(contextName);

		this.fIsComment = false;

		addResolver(new GlobalTemplateVariables.Dollar());
		addResolver(new GlobalTemplateVariables.Date());
		addResolver(new GlobalTemplateVariables.Year());
		addResolver(new GlobalTemplateVariables.Time());
		addResolver(new GlobalTemplateVariables.User());
		addResolver(new CodeTemplateContextType.Todo());
		addResolver(new CodeTemplateContextType.Copyright());

		if ("catchblock_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));

			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"exception_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_exceptiontype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"exception_var",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_exceptionvar));
		} else if ("methodbody_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"body_statement",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_bodystatement));
		} else if ("constructorbody_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"body_statement",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_bodystatement));
		} else if ("getterbody_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldname));
		} else if ("setterbody_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldname));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"param",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_param));
		} else if ("newtype_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"type_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typename));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"package_declaration",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_packdeclaration));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"type_declaration",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typedeclaration));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"typecomment",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typecomment));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"filecomment",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_filecomment));
			addCompilationUnitVariables();
		} else if (("classbody_context".equals(contextName))
				|| ("interfacebody_context".equals(contextName))
				|| ("enumbody_context".equals(contextName))
				|| ("annotationbody_context".equals(contextName))) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"type_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typename));
			addCompilationUnitVariables();
		} else if ("typecomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"type_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typename));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.TagsVariableResolver());
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("filecomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"type_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_typename));
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("fieldcomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_fieldtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_fieldname));
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("methodcomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"return_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_returntype));
			addResolver(new CodeTemplateContextType.TagsVariableResolver());
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("overridecomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"see_to_overridden",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_see_overridden_tag));
			addResolver(new CodeTemplateContextType.TagsVariableResolver());
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("delegatecomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"see_to_target",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_see_target_tag));
			addResolver(new CodeTemplateContextType.TagsVariableResolver());
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("constructorcomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.TagsVariableResolver());
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("gettercomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldname));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"bare_field_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_barefieldname));
			addCompilationUnitVariables();
			this.fIsComment = true;
		} else if ("settercomment_context".equals(contextName)) {
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field_type",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldtype));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"field",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_getterfieldname));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"enclosing_method",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_enclosingmethod));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"param",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_param));
			addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
					"bare_field_name",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_barefieldname));
			addCompilationUnitVariables();
			this.fIsComment = true;
		}
	}

	private void addCompilationUnitVariables() {
		addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
				"file_name",
				JavaTemplateMessages.CodeTemplateContextType_variable_description_filename));
		addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
				"package_name",
				JavaTemplateMessages.CodeTemplateContextType_variable_description_packagename));
		addResolver(new CodeTemplateContextType.CodeTemplateVariableResolver(
				"project_name",
				JavaTemplateMessages.CodeTemplateContextType_variable_description_projectname));
	}

	protected void validateVariables(TemplateVariable[] variables)
			throws TemplateException {
		ArrayList required = new ArrayList(5);
		String contextName = getId();
		if ("newtype_context".equals(contextName)) {
			required.add("package_declaration");
			required.add("type_declaration");
		}
		for (int i = 0; i < variables.length; i++) {
			String type = variables[i].getType();
			if (getResolver(type) == null) {
				String unknown = BasicElementLabels.getJavaElementName(type);
				throw new TemplateException(
						Messages.format(
								JavaTemplateMessages.CodeTemplateContextType_validate_unknownvariable,
								unknown));
			}
			required.remove(type);
		}
		if (!required.isEmpty()) {
			String missing = BasicElementLabels
					.getJavaElementName((String) required.get(0));
			throw new TemplateException(
					Messages.format(
							JavaTemplateMessages.CodeTemplateContextType_validate_missingvariable,
							missing));
		}
		super.validateVariables(variables);
	}

	public TemplateContext createContext() {
		return null;
	}

	public static void registerContextTypes(ContextTypeRegistry registry) {
		registry.addContextType(new CodeTemplateContextType(
				"catchblock_context"));
		registry.addContextType(new CodeTemplateContextType(
				"methodbody_context"));
		registry.addContextType(new CodeTemplateContextType(
				"constructorbody_context"));
		registry.addContextType(new CodeTemplateContextType(
				"getterbody_context"));
		registry.addContextType(new CodeTemplateContextType(
				"setterbody_context"));
		registry.addContextType(new CodeTemplateContextType("newtype_context"));
		registry.addContextType(new CodeTemplateContextType("classbody_context"));
		registry.addContextType(new CodeTemplateContextType(
				"interfacebody_context"));
		registry.addContextType(new CodeTemplateContextType("enumbody_context"));
		registry.addContextType(new CodeTemplateContextType(
				"annotationbody_context"));

		registry.addContextType(new CodeTemplateContextType(
				"filecomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"typecomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"fieldcomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"methodcomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"constructorcomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"overridecomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"delegatecomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"gettercomment_context"));
		registry.addContextType(new CodeTemplateContextType(
				"settercomment_context"));
	}

	public void validate(String pattern) throws TemplateException {
		super.validate(pattern);
		if ((this.fIsComment) && (!isValidComment(pattern)))
			throw new TemplateException(
					JavaTemplateMessages.CodeTemplateContextType_validate_invalidcomment);
	}

	private boolean isValidComment(String template) {
		IScanner scanner = ToolFactory.createScanner(true, false, false, false);
		scanner.setSource(template.toCharArray());
		try {
			int next = scanner.getNextToken();
			while (TokenScanner.isComment(next)) {
				next = scanner.getNextToken();
			}
			return next == 158;
		} catch (InvalidInputException localInvalidInputException) {
		}
		return false;
	}

	public static class TagsVariableResolver extends TemplateVariableResolver {
		public TagsVariableResolver() {
			super(
					"tags",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_tags);
		}

		protected String resolve(TemplateContext context) {
			return "@";
		}
	}

	public static class CodeTemplateVariableResolver extends
			TemplateVariableResolver {
		public CodeTemplateVariableResolver(String type, String description) {
			super(type, description);
		}

		protected String resolve(TemplateContext context) {
			return context.getVariable(getType());
		}
	}

	public static class Todo extends TemplateVariableResolver {
		public Todo() {
			super(
					"todo",
					JavaTemplateMessages.CodeTemplateContextType_variable_description_todo);
		}

		protected String resolve(TemplateContext context) {
			String todoTaskTag = StubUtility
					.getTodoTaskTag(((CodeTemplateContext) context)
							.getJavaProject());
			if (todoTaskTag == null) {
				return "XXX";
			}
			return todoTaskTag;
		}
	}
	
	/**
	 * 自定义模板
	 * @author {253587517@qq.com}
	 * @date   2015-1-6 上午11:24:18
	 */
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
}
