package org.overturetool.core.astanalysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.overture.ast.analysis.AnalysisException;
import org.overture.ast.analysis.DepthFirstAnalysisAdaptor;
import org.overture.ast.definitions.SClassDefinition;
import org.overture.ast.lex.Dialect;
import org.overture.ast.node.INode;
import org.overture.config.Release;
import org.overture.config.Settings;
import org.overture.parser.lex.LexException;
import org.overture.parser.syntax.ParserException;
import org.overture.typechecker.util.TypeCheckerUtil;
import org.overture.typechecker.util.TypeCheckerUtil.TypeCheckResult;

public class Main
{

	public static void main(String[] args) throws ParserException, LexException
	{
		if (args.length < 1)
		{
			System.err.println("No directory specified");
			System.exit(1);
		}

		File dir = new File(args[0]);

		if (dir.isFile())
		{
			System.err.println("Argument is not a directory");
			System.exit(1);
		}

		Collection<File> files = FileUtils.listFiles(dir, new RegexFileFilter(".+\\.vpp|.+\\.vdmrt"), DirectoryFileFilter.DIRECTORY);
		
		Settings.dialect = Dialect.VDM_RT;
		Settings.release = Release.VDM_10;

		TypeCheckResult<List<SClassDefinition>> res = TypeCheckerUtil.typeCheckRt(new ArrayList<File>(files));

		if (res.errors.isEmpty())
		{
			final Set<String> types = new HashSet<String>();

			for (INode node : res.result)
			{

				try
				{
					node.apply(new DepthFirstAnalysisAdaptor()
					{
						@Override
						public void defaultInINode(INode node)
								throws AnalysisException
						{
							types.add(node.getClass().getName());
						}

					});
				} catch (AnalysisException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			for (String string : types)
			{
				System.out.println(string);
			}
		} else
		{
			System.err.println(res.getErrorString());
			System.exit(1);
		}
	}

}
